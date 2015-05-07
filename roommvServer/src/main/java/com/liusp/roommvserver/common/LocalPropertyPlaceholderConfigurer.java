package com.liusp.roommvserver.common;

import java.util.Iterator;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class LocalPropertyPlaceholderConfigurer extends
		PropertyPlaceholderConfigurer {
	@Override
	protected void processProperties(
			ConfigurableListableBeanFactory beanFactoryToProcess,
			Properties props) throws BeansException {
		super.processProperties(beanFactoryToProcess, props);
		Iterator<Object> iterator =props.keySet().iterator();
		while (iterator.hasNext()) {
			String key = (String) iterator.next();
			System.setProperty(key, props.getProperty(key));
		}
	}
}
