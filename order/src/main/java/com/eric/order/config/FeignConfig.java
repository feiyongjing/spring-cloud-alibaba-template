package com.eric.order.config;

import feign.Contract;
import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {
    @Bean
    public Logger.Level feignLoggerLevel(){
//        return Logger.Level.NONE; // 不记录日志
//        return Logger.Level.BASIC; // 记录请求方法、URL、响应状态码和执行时间
//        return Logger.Level.HEADERS; // 在BASIC的基础上添加记录请求和响应的header
        return Logger.Level.FULL; // 在HEADERS的基础上添加记录请求和响应的body和元数据
    }

}
