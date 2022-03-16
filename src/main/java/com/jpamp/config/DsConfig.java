package com.jpamp.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DataSourceProperty;
import com.jpamp.common.DbContextHolder;
import com.jpamp.common.DynamicDataSource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

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
        Map<String, DataSourceProperty> propertys = properties.getDruid();
        DataSourceProperty dsp;
        int i = 0;
        for (Map.Entry<String, DataSourceProperty> p : propertys.entrySet()) {
            dsp = p.getValue();
            DruidDataSource dataSource = new DruidDataSource();
            dataSource.setUsername(dsp.getUsername());
            dataSource.setPassword(dsp.getPassword());
            dataSource.setUrl(dsp.getUrl());
            dataSource.setName(dsp.getPoolName());
            String driverClassName = dsp.getDriverClassName();
            if (StringUtils.isNotEmpty(driverClassName)) {
                dataSource.setDriverClassName(driverClassName);
            }
            targetDataSources.put(p.getKey(), dataSource);
            DbContextHolder.TENANT_DB.put(String.valueOf(i++), p.getKey());
        }
        dynamicDataSource.setTargetDataSources(targetDataSources);
        dynamicDataSource.setDefaultTargetDataSource(targetDataSources.values().stream().findFirst().get());
        return dynamicDataSource;
    }


    /**
     * 事物配置
     * @param dataSource
     * @return
     */
    @Primary
    @Bean(name = "multipleTransactionManager")
    public DataSourceTransactionManager multipleTransactionManager(@Qualifier("multipleDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
