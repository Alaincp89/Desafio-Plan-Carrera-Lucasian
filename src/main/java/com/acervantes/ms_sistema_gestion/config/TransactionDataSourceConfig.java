package com.acervantes.ms_sistema_gestion.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.acervantes.ms_sistema_gestion.transaction.repository",
        entityManagerFactoryRef = "transactionEntityManagerFactory",
        transactionManagerRef = "transactionTransactionManager"
)
public class TransactionDataSourceConfig {


    @Primary
    @Bean(name = "transactionDataSource")
    public DataSource transactionDataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/transaction_db");
        config.setUsername("root");
        config.setPassword("");
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        return new HikariDataSource(config);
    }


    @Primary
    @Bean(name = "transactionEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean transactionEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("transactionDataSource") DataSource dataSource) {
        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");

        return builder
                .dataSource(dataSource)
                .packages("com.acervantes.ms_sistema_gestion.transaction.entity")
                .persistenceUnit("transaction")
                .properties(properties)
                .build();
    }

    @Primary
    @Bean(name = "transactionTransactionManager")
    public PlatformTransactionManager transactionTransactionManager(
            @Qualifier("transactionEntityManagerFactory") LocalContainerEntityManagerFactoryBean transactionEntityManagerFactory) {
        return new JpaTransactionManager(transactionEntityManagerFactory.getObject());
    }
}

