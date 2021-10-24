package com.app.config;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.client.RestTemplate;

@ComponentScan("com.app")
@org.springframework.context.annotation.Configuration
public class Configuration {

  @Bean
  public RestTemplate restTemplateBean() {
    return new RestTemplate();
  }

  @Bean
  public DataSource dataSource() {
    DriverManagerDataSource ds = new DriverManagerDataSource();
    ds.setDriverClassName(com.mysql.jdbc.Driver.class.getName());
    
    return ds;
  }
}
