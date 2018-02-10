package com.cssrc.warehouse.configuration;

import com.cssrc.warehouse.config.ClientConfig;
import com.cssrc.warehouse.config.JwtConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan({"com.cssrc.zuul"})
public class AutoConfiguration {
    @Bean
    ClientConfig getClientConfig(){
        return new ClientConfig();
    }

    @Bean
    JwtConfig getJwtonfig(){
        return new JwtConfig();
    }
}
