package com.jpamp.config.jpa;

import com.jpamp.context.Context;
import jakarta.annotation.Resource;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;
import java.util.Map;


/**
 * jpa配置
 * @Description
 * @author YX
 * @Date 2022/3/20 20:43
 */
@Configuration
@EnableConfigurationProperties(JpaProperties.class)
@EnableJpaRepositories(basePackages = {Context.JPA_REPOSITORY},
        entityManagerFactoryRef = "entityManagerFactory",
        transactionManagerRef = "multipleTransactionManager")
public class JpaEntityManager {

    @Resource
    private JpaProperties jpaProperties;

    @Resource
    private DataSource multipleDataSource;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(EntityManagerFactoryBuilder builder) {
        // 不明白为什么这里获取不到 application.yml 里的配置
        Map<String, String> properties = jpaProperties.getProperties();
        //要设置这个属性，实现 CamelCase -> UnderScore 的转换
        properties.put("hibernate.implicit_naming_strategy",
                "org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy");
        return builder
                .dataSource(multipleDataSource)
                .properties(properties)
                .packages(Context.ENTITY_PACKAGES)
                .persistenceUnit("myPersistenceUnit")
                .build();
    }


    @Bean
    @Primary
    public EntityManagerFactory entityManagerFactory(EntityManagerFactoryBuilder builder) {
        return this.entityManagerFactoryBean(builder).getObject();
    }

}
