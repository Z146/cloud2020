package com.wuhua.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyRule {

    // 在主启动类上添加该注解 @RibbonClient(name = "CLOUD-PAYMENT-SERVICE",configuration = MyRule.class)
    @Bean
    public IRule myRule(){
        // 随机
        return new RandomRule();
    }
}
