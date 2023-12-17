//package com.es.kalaha.infrastructure.data;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//
//@Autowired Environment env;
//
//@Bean
//public SqLiteDataSource dataSource() {
//final DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName(env.getProperty("driverClassName"));
//        dataSource.setUrl(env.getProperty("url"));
//        dataSource.setUsername(env.getProperty("user"));
//        dataSource.setPassword(env.getProperty("password"));
//        return dataSource;
//        }