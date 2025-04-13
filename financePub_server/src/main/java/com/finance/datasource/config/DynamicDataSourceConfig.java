package com.finance.datasource.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * 单数据源配置
 */
@Configuration
public class DynamicDataSourceConfig {

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.druid") // 指向统一的配置前缀
    public DataSource dataSource() {
        return DruidDataSourceBuilder.create().build();
    }
}