package com.liusp.roommvserver.common;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class WebApplicationListener implements ServletContextListener {
	private static WebApplicationContext webApplicationContext;
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		webApplicationContext = WebApplicationContextUtils
				.getWebApplicationContext(sce.getServletContext());
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub

	}

	public static WebApplicationContext getWebApplicationContext() {
		return webApplicationContext;
	}
}
