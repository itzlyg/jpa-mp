package com.jpamp.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "spring")
public class DynamicDsProperties {

    /**
     * 每一个数据源
     */
    private Map<String, DataSourceProperty> datasource;

    public Map<String, DataSourceProperty> getDatasource() {
        return datasource;
    }

    public void setDatasource(Map<String, DataSourceProperty> datasource) {
        this.datasource = datasource;
    }
}
