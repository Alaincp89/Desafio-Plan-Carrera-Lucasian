package com.acervantes.ms_sistema_gestion.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.acervantes.ms_sistema_gestion.parameter.repository",
        entityManagerFactoryRef = "parameterEntityManagerFactory",
        transactionManagerRef = "parameterTransactionManager"
)
public class ParameterDataSourceConfig {

    @Bean(name = "parameterDataSource")
    public DataSource parameterDataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/parameter_db");
        config.setUsername("root");
        config.setPassword("");
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        return new HikariDataSource(config);
    }

    @Bean(name = "parameterEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean parameterEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("parameterDataSource") DataSource dataSource) {
        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");

        return builder
                .dataSource(dataSource)
                .packages("com.acervantes.ms_sistema_gestion.parameter.entity")
                .persistenceUnit("parameter")
                .properties(properties)
                .build();
    }

    @Bean(name = "parameterTransactionManager")
    public PlatformTransactionManager parameterTransactionManager(
            @Qualifier("parameterEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
