package com.softserve.edu.Resources.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

import static org.hibernate.cfg.AvailableSettings.*;

@Configuration
@EnableTransactionManagement
// Load to Environment.
@PropertySource("classpath:database.properties")
public class DBConfig {
    // The Environment class serves as the property holder
    // and stores all the properties loaded by the @PropertySource
    @Autowired
    private Environment env;
    @Bean
    public LocalContainerEntityManagerFactoryBean getEntityManagerFactoryBean() {
        LocalContainerEntityManagerFactoryBean lcemfb = new LocalContainerEntityManagerFactoryBean();
        lcemfb.setJpaVendorAdapter(getJpaVendorAdapter());
        lcemfb.setPersistenceUnitName("myJpaPersistenceUnit");
        lcemfb.setDataSource(getDataSource());
        lcemfb.setJpaProperties(jpaProperties());
        lcemfb.setPackagesToScan("com.softserve.edu.Resources.entity");
        return lcemfb;
    }
    @Bean
    public JpaVendorAdapter getJpaVendorAdapter() {
        JpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        return adapter;
    }
    @Bean
    public DataSource getDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(env.getProperty("database.driverClassName"));
        dataSource.setUrl(env.getProperty("database.url"));
        dataSource.setUsername(env.getProperty("database.username"));
        dataSource.setPassword(env.getProperty("database.password"));
        return dataSource;
    }
    @Bean
    public PlatformTransactionManager txManager(){
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager(
                getEntityManagerFactoryBean().getObject());
        return jpaTransactionManager;
    }

    private Properties jpaProperties() {
        Properties properties = new Properties();
        properties.put(DIALECT, env.getProperty(DIALECT));
        properties.put(SHOW_SQL, env.getProperty(SHOW_SQL));
        properties.put(FORMAT_SQL, env.getProperty(FORMAT_SQL));
        properties.put(USE_SQL_COMMENTS, env.getProperty(USE_SQL_COMMENTS));
        properties.put(HBM2DDL_AUTO, env.getProperty(HBM2DDL_AUTO));
        properties.put("persistence.sql-load-script-source", env.getProperty("persistence.sql-load-script-source"));
        return properties;
    }
}

//POSTGRES HEROKU CONFIG
//throws URISyntaxException
       /* URI dbUri = new URI(System.getenv("DATABASE_URL"));
        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + dbUri.getPath();
        dataSource.setDriverClassName(env.getProperty("ds.database-driver"));
        dataSource.setUrl(dbUrl);
        dataSource.setUsername(username);
        dataSource.setPassword(password);*/