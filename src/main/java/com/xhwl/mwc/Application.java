package com.xhwl.mwc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
//    private CorsConfiguration buildConfig() { 
//        CorsConfiguration corsConfiguration = new CorsConfiguration(); 
//        corsConfiguration.addAllowedOrigin("*"); 
//        corsConfiguration.addAllowedHeader("*"); 
//        corsConfiguration.addAllowedMethod("*"); 
//        return corsConfiguration; 
//      } 
//      /** 
//       * 跨域过滤器 
//       * @return 
//       */ 
//      @Bean 
//      public CorsFilter corsFilter() { 
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(); 
//        source.registerCorsConfiguration("/**", buildConfig()); // 4 
//        return new CorsFilter(source); 
//      } 
}
