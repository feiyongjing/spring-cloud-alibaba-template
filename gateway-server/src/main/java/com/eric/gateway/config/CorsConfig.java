package com.eric.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@Configuration
public class CorsConfig {
    @Bean
    public CorsWebFilter corsFilter(){
        CorsConfiguration config=new CorsConfiguration();
        config.addAllowedMethod("*");   // 跨域允许的方法
        config.addAllowedOrigin("*");   // 跨域允许的来源
        config.addAllowedHeader("*");   // 跨域允许的请求头

        UrlBasedCorsConfigurationSource source=new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",config); // 允许跨域访问的资源
        return new CorsWebFilter(source);
    }
}
