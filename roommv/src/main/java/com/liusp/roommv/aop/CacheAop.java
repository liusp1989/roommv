package com.liusp.roommv.aop;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.liusp.roommv.annotation.Cache;
import com.liusp.roommv.annotation.Key;
import com.liusp.roommv.common.RoommvConstant;

@Component
@Aspect
public class CacheAop {
	public static final Logger logger = Logger.getLogger(CacheAop.class);
	private JdkSerializationRedisSerializer jdkSerializer = new JdkSerializationRedisSerializer();
	private StringRedisSerializer stringSerializer = new StringRedisSerializer();
	@Resource(name = "redisTemplate")
	private RedisTemplate<Object, Object> redisTemplate;

	// @Pointcut(value = "@annotation(com.liusp.roommv.annotation.Cache)")
	@Pointcut("execution(* com.liusp.roommv..*.*(..)) && @annotation(com.liusp.roommv.annotation.Cache)")
	public void useCache() {
	}

	@Before(value = "useCache()")
	public void doBefore() {
	}

	@SuppressWarnings("rawtypes")
	@Around(value = "useCache()")
	public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
		Object result = null;
		MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
		Method method = methodSignature.getMethod();
		Cache cache = method.getAnnotation(Cache.class);
		if (cache.enable()) {// 缓存是否启用
			Annotation[][] annotations = method.getParameterAnnotations();
			boolean isKeyUnique = isKeyUnique(annotations);
			String cacheKey = "";
			Class returnType = method.getReturnType();
			cacheKey = getCacheKey(pjp, method, cache, annotations);
			if (isKeyUnique) {// 主键是否唯一
				logger.info("#############尝试缓存中获取数据#################");
				result = getCacheByKey(cacheKey, returnType);
			} else {
				logger.info("#############尝试缓存中获取数据#################");
				Set<Object> keysSet = redisTemplate.keys(cacheKey + "*");
				if (keysSet.size() != 0) {
					List<Object> objects = new ArrayList<Object>();
					for (Object K : keysSet) {
						objects.add(getCacheByKey(K, returnType));
					}
					if (!CollectionUtils.isEmpty(objects)) {
						if (returnType.newInstance() instanceof Collection) {
							result = objects;
						} else {
							result = objects.get(0);
						}
					}
				}
			}
			if (result == null) {// 缓存没有就去数据库取
				logger.info("#############尝试缓存中获取数据失败,前往数据库获取数据#################");
				if (!isKeyUnique) {
					cacheKey += RoommvConstant.CACHE_KEY_SEPARATER
							+ new Date().getTime();
				}
				result = pjp.proceed();
				redisTemplate.setValueSerializer(jdkSerializer);
				redisTemplate.afterPropertiesSet();
				redisTemplate.opsForValue().set(cacheKey, result, 24,
						TimeUnit.HOURS);
			}
		} else {
			pjp.proceed();
		}
		return result;
	}

	/**
	 * 根据key获取缓存
	 * 
	 * @param k
	 * @param returnType
	 * @return
	 */
	private Object getCacheByKey(Object k, Class returnType) {
		Object result;
		if (returnType.equals(String.class)) {
			result = redisTemplate.opsForValue().get(k);
		} else {
			redisTemplate.setValueSerializer(jdkSerializer);
			redisTemplate.afterPropertiesSet();
			result = redisTemplate.opsForValue().get(k);
		}
		return result;
	}

	/**
	 * 判断方法参数是否提供唯一标识主键
	 * 
	 * @param annotations
	 * @return
	 */
	private boolean isKeyUnique(Annotation[][] annotations) {
		// TODO Auto-generated method stub
		boolean isKeyUnique = false;
		int arrayCount = annotations.length;
		if (arrayCount != 0) {
			for (int i = 0; i < arrayCount; i++) {
				Annotation[] ann = annotations[i];
				int len = ann.length;
				for (int j = 0; j < len && !isKeyUnique; j++) {
					Annotation annotation = ann[j];
					if (Key.class.getName().equals(
							annotation.annotationType().getName())) {
						Key key = (Key) annotation;
						if (key.unique()) {
							isKeyUnique = true;
							break;
						}
					}
				}
			}
		} else {
			logger.error("方法不包含@key注解，无法启用缓存");
			throw new RuntimeException("方法不包含@key注解，无法启用缓存");
		}
		return false;
	}

	/**
	 * 根据方法参数生成主键
	 * 
	 * @param pjp
	 * @param method
	 * @param cache
	 * @param annotations
	 * @return
	 */
	private String getCacheKey(ProceedingJoinPoint pjp, Method method,
			Cache cache, Annotation[][] annotations) {
		String cacheKey = cache.table() + RoommvConstant.CACHE_KEY_SEPARATER
				+ cache.keyField() + RoommvConstant.CACHE_KEY_SEPARATER;
		if (annotations.length == 0) {
			logger.error("方法不包含@key注解，无法启用缓存");
			throw new RuntimeException("方法不包含@key注解，无法启用缓存");
		}
		Map<Integer, Key> indexAndKeyMap = getIndexKeyMap(annotations);
		Object[] objects = pjp.getArgs();
		if (indexAndKeyMap.size() != 0) {
			cacheKey += getCacheKeySuffix(indexAndKeyMap, objects);
		} else {
			logger.error("方法不包含@key注解，无法启用缓存");
			throw new RuntimeException("方法不包含@key注解，无法启用缓存");
		}
		return cacheKey;
	}

	/**
	 * 获取标注了@key的参数的下标和key的map
	 * 
	 * @param annotations
	 * @return
	 */
	private Map<Integer, Key> getIndexKeyMap(Annotation[][] annotations) {
		int arrayCount = annotations.length;
		Map<Integer, Key> indexAndKeyMap = new HashMap<Integer, Key>();
		for (int i = 0; i < arrayCount; i++) {
			Annotation[] ann = annotations[i];
			int len = ann.length;
			for (int j = 0; j < len; j++) {
				Annotation annotation = ann[j];
				if (Key.class.getName().equals(
						annotation.annotationType().getName())) {
					Key key = (Key) annotation;
					indexAndKeyMap.put(i, key);
				}
			}
		}
		return indexAndKeyMap;
	}

	/**
	 * 根据标注@key的方法参数生成缓存主键后缀
	 * 
	 * @param indexAndKeyMap
	 * @param args
	 * @return
	 */
	private String getCacheKeySuffix(Map<Integer, Key> indexAndKeyMap,
			Object[] args) {
		String cacheKeySuffix = "";
		for (Integer index : indexAndKeyMap.keySet()) {
			Object object = args[index];
			Key annkey = indexAndKeyMap.get(index);
			if (object instanceof Map) {
				Map map = (Map) object;
				for (Object key : map.keySet()) {
					Object valueObject = map.get(key);
					String valuestr = "";
					try {
						valuestr = String.valueOf(valueObject);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						logger.error("@key注解包含不能转化为string类型的参数");
						throw new RuntimeException("@key注解包含不能转化为string类型的参数");
					}
					cacheKeySuffix += valuestr;
				}
			} else {
				String valuestr = "";
				try {
					valuestr = String.valueOf(object);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					logger.error("@key注解包含不能转化为string类型的参数");
					throw new RuntimeException("@key注解包含不能转化为string类型的参数");
				}
				cacheKeySuffix += valuestr;
			}
		}
		return cacheKeySuffix;
	}

	@After(value = "useCache()")
	public void doAfter() {
	}
}
