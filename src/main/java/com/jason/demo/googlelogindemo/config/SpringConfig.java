package com.jason.demo.googlelogindemo.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
	@Bean
	public FilterRegistrationBean<FilterConfig> loggingFilter(){
	    FilterRegistrationBean<FilterConfig> registrationBean 
	      = new FilterRegistrationBean<>();
	        
	    registrationBean.setFilter(new FilterConfig());
	    registrationBean.addUrlPatterns("/home");
	        
	    return registrationBean;    
	}
}
