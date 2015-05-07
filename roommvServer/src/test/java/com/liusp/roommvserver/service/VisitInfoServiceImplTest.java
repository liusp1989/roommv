
package com.liusp.roommvserver.service; 

import com.liusp.roommvserver.entity.VisitInfo;
import com.liusp.roommvserver.service.redis.RedisVisitInfoService;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/** 
* VisitInfoServiceImpl Tester. 
* 
* @author <Authors name> 
* @since <pre>һ�� 27, 2015</pre> 
* @version 1.0 
*/ 
@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/applicationContext.xml" })
public class VisitInfoServiceImplTest {
    @Resource
    private RedisVisitInfoService redisVisitInfoService;
@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: addVisitInfo(VisitInfo visitInfo) 
* 
*/ 
@Test
public void testAddVisitInfoVisitInfo() throws Exception {
//    SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//    for (int i=0;i<1000;i++) {
//        VisitInfo visitInfo = new VisitInfo();
//        visitInfo.setCreateDate(sdf.format(new Date()));
//        visitInfo.setCurrentUrl("127.0.0.1");
//        visitInfo.setIp("127.0.0.1");
//        visitInfo.setReferUrl("127.0.0.1");
//        visitInfo.setUserId("127.0.0.1");
//        redisVisitInfoService.addVisitInfo(visitInfo);
//    }
   String value= redisVisitInfoService.getVisitInfo("test");
    System.out.println(value);
} 

/** 
* 
* Method: addVisitInfo(String jsonMessage) 
* 
*/ 
@Test
public void testAddVisitInfoJsonMessage() throws Exception { 
//TODO: Test goes here... 
} 


} 
