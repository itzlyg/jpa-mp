package com.jpamp.config.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Map;

@Configuration
@EnableConfigurationProperties(JpaProperties.class)
@EnableJpaRepositories(basePackages = {"com.jpamp.system.repository"},
        entityManagerFactoryRef = "entityManagerFactory",
        transactionManagerRef = "multipleTransactionManager")
public class JpaEntityManager {

    @Autowired
    private JpaProperties jpaProperties;

    @Autowired
    private DataSource multipleDataSource;

    /**
     * 基类包
     */
    private static final String ENTITY_PACKAGES = "com.jpamp.system.entity";


    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(EntityManagerFactoryBuilder builder) {
        // 不明白为什么这里获取不到 application.yml 里的配置
        Map<String, String> properties = jpaProperties.getProperties();
        //要设置这个属性，实现 CamelCase -> UnderScore 的转换
        properties.put("hibernate.physical_naming_strategy",
                "org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy");
        return builder
                .dataSource(multipleDataSource)
                .properties(properties)
                .packages(ENTITY_PACKAGES)
                .persistenceUnit("myPersistenceUnit")
                .build();
    }


    @Bean
    @Primary
    public EntityManagerFactory entityManagerFactory(EntityManagerFactoryBuilder builder) {
        return this.entityManagerFactoryBean(builder).getObject();
    }

}
