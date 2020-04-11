package com.wuhua.springcloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    // 配置 feign 的日志打印
    @Bean
    Logger.Level level(){
        return Logger.Level.FULL;
    }
}
