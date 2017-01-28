package com.springcookbook.config.core;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.springcookbook.config.AppConfig;
import com.springcookbook.config.DatasourceConfig;
import com.springcookbook.config.HibernateConfig;
import com.springcookbook.config.SecurityConfig;

public class ServletInitializer extends
		AbstractAnnotationConfigDispatcherServletInitializer {
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] {SecurityConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { 
				AppConfig.class, 
				DatasourceConfig.class,
				HibernateConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
}
