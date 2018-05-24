package com.xhwl.mwc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * ajax跨域工具
 * @author jiayiwu
 * @date 2017年12月25日
 */
@Configuration
public class CorsConfig {
	 private CorsConfiguration buildConfig() {
	        CorsConfiguration corsConfiguration = new CorsConfiguration();
	        corsConfiguration.addAllowedOrigin("*"); // 1
	        corsConfiguration.addAllowedHeader("*"); // 2
	        corsConfiguration.addAllowedMethod("*"); // 3
	      //corsConfiguration.setAllowCredentials(true);
	        return corsConfiguration;
	    }

	    @Bean
	    public CorsFilter corsFilter() {
	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        source.registerCorsConfiguration("/**", buildConfig()); // 4
	        return new CorsFilter(source);
	    }
}
