package com.springcookbook.config;

import java.util.Locale;

import javax.sql.DataSource;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.springcookbook.web.interceptor.PerformanceInterceptor;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan(basePackages = { 
		"com.springcookbook.web.controller", 
		"com.springcookbook.dao", 
		"com.springcookbook.service" })
@Import({ SecurityConfig.class })
public class AppConfig extends WebMvcConfigurerAdapter{
	
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/visit_card");
		dataSource.setUsername("root");
		dataSource.setPassword("root");

		return dataSource;
	}
	
	@Bean
	public ViewResolver jspViewResolver(){
	    InternalResourceViewResolver resolver = new InternalResourceViewResolver();
	    resolver.setViewClass(JstlView.class);
	    resolver.setPrefix("/WEB-INF/pages/");
	    resolver.setSuffix(".jsp");
	    return resolver;
	}
	
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:/messages");
		messageSource.setUseCodeAsDefaultMessage(true);
		
		return messageSource;
	}
	
	@Bean
	public HandlerInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName("lang");
		return localeChangeInterceptor;
	}
	
	@Bean
	public HandlerInterceptor performaInterceptor() {
		PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
		return performanceInterceptor;
	}
	
	@Bean
	public LocaleResolver localeResolver() {
	  CookieLocaleResolver localeResolver = new CookieLocaleResolver();
	  localeResolver.setDefaultLocale(new Locale("en"));
	  return localeResolver;
	}
		
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeChangeInterceptor());
		registry.addInterceptor(performaInterceptor()).addPathPatterns("/user/list");
	}
	
}
