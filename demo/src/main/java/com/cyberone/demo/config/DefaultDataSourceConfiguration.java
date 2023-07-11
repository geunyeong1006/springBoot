//package com.cyberone.demo.config;
//
//import javax.sql.DataSource;
//
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.SqlSessionTemplate;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//
//@Configuration
//@MapperScan(basePackages = {"com.cyberone.demo.repository"}, sqlSessionTemplateRef = "db1SqlSessionTemplate")
//public class DefaultDataSourceConfiguration {
//    @Bean
//    @Primary
//    @Qualifier("db1DataSource")
//    @ConfigurationProperties(prefix = "spring.datasource")
//    public DataSource db1DataSource() {
//        return DataSourceBuilder.create().build();
//    }
//
//    @Primary
//    @Bean(name = "d1SqlSessionFactory")    
//    public SqlSessionFactory db1SqlSessionFactory(@Qualifier("db1DataSource") DataSource db1DataSource) throws Exception {
//        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
//        sessionFactory.setDataSource(db1DataSource);
//        sessionFactory.setMapperLocations(
//              new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/*.xml"));
//        return sessionFactory.getObject();
//    }
//
//    @Primary
//    @Bean(name = "db1SqlSessionTemplate")
//    public SqlSessionTemplate db1SqlSessionTemplate(@Qualifier("d1SqlSessionFactory") SqlSessionFactory db1SqlSessionFactory) {
//        return new SqlSessionTemplate(db1SqlSessionFactory);
//    }
//}