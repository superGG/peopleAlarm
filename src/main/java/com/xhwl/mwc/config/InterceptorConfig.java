package com.xhwl.mwc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.xhwl.mwc.interceptor.IpInterceptor;

/**
 * 添加拦截器
 * @author Kellan_Song
 * @createTime 2018年3月27日
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {

	 	@Override
	    public void addInterceptors(InterceptorRegistry registry) {
	        // 多个拦截器组成一个拦截器链
	        // addPathPatterns 用于添加拦截规则
	        // excludePathPatterns 用户排除拦截
	        registry.addInterceptor(new IpInterceptor()).addPathPatterns("/api/**");
	        super.addInterceptors(registry);
	    }

}
