package ru.beljankin.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Configuration
@ComponentScan("ru")
@PropertySource("classpath:db.properties")
@EnableTransactionManagement(proxyTargetClass = true)

public class DataConfig {
        @Autowired
        private Environment env;

        @Bean
        public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
            LocalContainerEntityManagerFactoryBean em
                    = new LocalContainerEntityManagerFactoryBean();
            em.setDataSource(dataSource());
            System.out.println("db.packages.to.scan :" + env.getRequiredProperty("db.packages.to.scan"));
//            em.setPackagesToScan(new String[] { "db.packages.to.scan" });
            em.setPackagesToScan(env.getRequiredProperty("db.packages.to.scan"));
            JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
            em.setJpaVendorAdapter(vendorAdapter);
            em.setJpaProperties(additionalProperties());

            return em;

        }

    Properties additionalProperties() {
     //   Properties properties = new Properties();
     /*
        System.out.println(env.getRequiredProperty("db.hibernate.hbm2ddl.auto"));
        properties.setProperty(env.getRequiredProperty("db.hibernate.hbm2ddl.auto"), "db.hibernate.hbm2ddl.auto");

        System.out.println(env.getRequiredProperty("db.hibernate.dialect"));
        properties.setProperty(env.getRequiredProperty("db.hibernate.dialect"), "db.hibernate.dialect");


        return properties;

      */

                try {
            Properties properties = new Properties();
            InputStream is = getClass().getClassLoader().getResourceAsStream("hibernate.properties");
            properties.load(is);
            System.out.println("properties : " + properties.toString());
            return properties;
        } catch (IOException e) {
            throw new IllegalArgumentException("File 'hibernate.properties' doesn't exist!", e);
        }

    }

        @Bean
        public DataSource dataSource(){
            DriverManagerDataSource dataSource = new DriverManagerDataSource();
//            dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
//            dataSource.setUrl("db.url");
//            dataSource.setUsername( "db.username" );
//            dataSource.setPassword( "db.password" );
            System.out.println("db.driver :" + env.getRequiredProperty("db.driver"));
            dataSource.setDriverClassName(env.getRequiredProperty("db.driver"));

            System.out.println("db.url :" + env.getRequiredProperty("db.url"));
            dataSource.setUrl(env.getRequiredProperty("db.url"));

            System.out.println("db.username :" + env.getRequiredProperty("db.username"));
            dataSource.setUsername(env.getRequiredProperty("db.username"));

            System.out.println("db.password :" + env.getRequiredProperty("db.password"));
            dataSource.setPassword(env.getRequiredProperty("db.password"));

            return dataSource;
        }

        @Bean
        public PlatformTransactionManager transactionManager() {
            JpaTransactionManager transactionManager = new JpaTransactionManager();
            transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());

            return transactionManager;
        }
    }

