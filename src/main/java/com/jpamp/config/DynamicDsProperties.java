package com.jpamp.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "spring.datasource")
public class DynamicDsProperties {

    /**
     * 每一个数据源
     */
    private Map<String, DataSourceProperty> druid;

    public Map<String, DataSourceProperty> getDruid() {
        return druid;
    }

    public void setDruid(Map<String, DataSourceProperty> druid) {
        this.druid = druid;
    }
}
