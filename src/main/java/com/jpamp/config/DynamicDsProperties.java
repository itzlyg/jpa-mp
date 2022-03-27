package com.jpamp.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "spring")
public class DynamicDsProperties {

    /**
     * 每一个数据源
     */
    private Map<String, DriverManagerDataSource> datasource;

    public Map<String, DriverManagerDataSource> getDatasource() {
        return datasource;
    }

    public void setDatasource(Map<String, DriverManagerDataSource> datasource) {
        this.datasource = datasource;
    }
}
