package com.postman1.postman1.config;//package config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//import org.springframework.transaction.PlatformTransactionManager;
//
//import javax.sql.DataSource;
//
//public class AppConfig {
//    @Bean
//    //spring에서 데이터베이스와 커넥션을 관리하고 제공하는 빈
//    public DataSource dataSource(){
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setUsername("root");
//        dataSource.setPassword("12341234");
//        //mySQL에 드라이버 클래스
//        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
//        dataSource.setUrl("jdbc:mysql://localhost:3306/b");
//        return dataSource;
//    }
//
//    @Bean
//    //jdbc template 등록
//    public JdbcTemplate jdbcTemplate() { return new JdbcTemplate(dataSource()); }
//
//    @Bean
//    //datasource 안에 트랜젝션 등록
//    public PlatformTransactionManager transactionManager(){
//        return new DataSourceTransactionManager(dataSource());
//    }
//}
