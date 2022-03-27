package com.jpamp.config;

import com.jpamp.common.DbContextHolder;
import com.jpamp.common.DynamicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DsConfig {

    @Autowired
    private DynamicDsProperties properties;

    /**
     * 动态数据源配置
     *
     * @return
     */
    @Bean
    @Primary
    public DataSource multipleDataSource() {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        Map<Object, Object> targetDataSources = new HashMap<>();
        Map<String, DriverManagerDataSource> datasources = properties.getDatasource();
        int i = 0;
        for (Map.Entry<String, DriverManagerDataSource> p : datasources.entrySet()) {
            targetDataSources.put(p.getKey(), p.getValue());
            DbContextHolder.TENANT_DB.put(String.valueOf(i++), p.getKey());
        }
        dynamicDataSource.setTargetDataSources(targetDataSources);
        // 默认设置第一个连接为默认数据库
        dynamicDataSource.setDefaultTargetDataSource(targetDataSources.values().stream().findFirst().get());
        return dynamicDataSource;
    }


    /**
     * 事物配置
     *
     * @param dataSource
     * @return
     */
    @Bean
    @Primary
    public DataSourceTransactionManager multipleTransactionManager(@Qualifier("multipleDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
