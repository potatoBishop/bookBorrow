package com.etoak.java.config;

import com.etoak.java.filter.MyGlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*** * @Author 高俊 QQ:1120934832 * @Slogan 易途科技，精英启航 */
@Configuration
public class GatewayConfig {
    @Bean
    public MyGlobalFilter myGlobalFilter(){
    return new MyGlobalFilter();
    }
}