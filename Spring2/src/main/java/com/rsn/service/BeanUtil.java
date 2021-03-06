package com.rsn.service;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

/**
 * 
 * @author Gabriel Ferrer
 *	Is a class that makes spring beans importable in a class that is not injectable
 */

@Service
public class BeanUtil implements ApplicationContextAware {
    private static ApplicationContext context;
 
    public static <T> T getBean(Class<T> beanClass) {
        return context.getBean(beanClass);
    }
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		context = applicationContext;	
	}
}
