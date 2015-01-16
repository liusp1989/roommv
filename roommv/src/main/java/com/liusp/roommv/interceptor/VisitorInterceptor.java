package com.liusp.roommv.interceptor;

import io.netty.channel.Channel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.liusp.roommv.socket.SocketClient;
import com.liusp.roommv.vo.VisitInfo;

public class VisitorInterceptor extends HandlerInterceptorAdapter {
	private static final Logger logger = Logger
			.getLogger(VisitorInterceptor.class);
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss ");
		Map<String, Object> busMap = new HashMap<String, Object>();
		busMap.put("bizType", "visitInfo");
		String referUrl = request.getHeader("Referer");
		String currentUrl = request.getRequestURL().toString();
		String ip = this.getIpAddr(request);
		String userId = (String) request.getSession().getAttribute("userId");
		VisitInfo visitInfo = new VisitInfo();
		visitInfo.setCurrentUrl(currentUrl);
		visitInfo.setIp(ip);
		visitInfo.setReferUrl(referUrl);
		visitInfo.setUserId(userId);
		visitInfo.setCreateDate(sdf.format(new Date()));
		busMap.put("visitInfo", visitInfo);
		try {
			Channel channel = SocketClient.getChannelHandler().getChannel();
			if (channel.isOpen()) {
				channel.writeAndFlush(JSONObject.wrap(busMap).toString());
			} else {
				throw new RuntimeException("客户端通道已关闭，无法传输数据");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("客户端启动失败，无法传输数据");
		}
		return true;
	}

	public String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
}