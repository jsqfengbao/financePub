package com.finance.datasource.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.finance.datasource.properties.DynamicDataSourceProperties;
import com.finance.datasource.properties.DataSourceNames;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 配置多数据源
 *
 * @author Mark sunlightcs@gmail.com
 */
@Configuration
@EnableConfigurationProperties(DynamicDataSourceProperties.class)
public class DynamicDataSourceConfig {

    @Bean
    @ConfigurationProperties("spring.datasource.druid.first")
    public DataSource firstDataSource(){
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.druid.second")
    public DataSource secondDataSource(){
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    public DynamicDataSource dataSource(DataSource firstDataSource, DataSource secondDataSource) {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceNames.FIRST, firstDataSource);
        targetDataSources.put(DataSourceNames.SECOND , secondDataSource);
        return new DynamicDataSource(firstDataSource, targetDataSources);
    }
//    @Autowired
//    private DynamicDataSourceProperties properties;
//
//    @Bean
//    @ConfigurationProperties(prefix = "spring.datasource.druid")
//    public DataSourceProperties dataSourceProperties() {
//        return new DataSourceProperties();
//    }
//
//    @Bean
//    public DynamicDataSource dynamicDataSource(DataSourceProperties dataSourceProperties) {
//        DynamicDataSource dynamicDataSource = new DynamicDataSource();
//        dynamicDataSource.setTargetDataSources(getDynamicDataSource());
//
//        //默认数据源
//        DruidDataSource defaultDataSource = DynamicDataSourceFactory.buildDruidDataSource(dataSourceProperties);
//        dynamicDataSource.setDefaultTargetDataSource(defaultDataSource);
//
//        return dynamicDataSource;
//    }
//
//    private Map<Object, Object> getDynamicDataSource(){
//        Map<String, DataSourceProperties> dataSourcePropertiesMap = properties.getDatasource();
//        Map<Object, Object> targetDataSources = new HashMap<>(dataSourcePropertiesMap.size());
//        dataSourcePropertiesMap.forEach((k, v) -> {
//            DruidDataSource druidDataSource = DynamicDataSourceFactory.buildDruidDataSource(v);
//            targetDataSources.put(k, druidDataSource);
//        });
//
//        return targetDataSources;
//    }

}