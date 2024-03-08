package kr.co.ch05.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class JdbcConfig {

    @Bean
    public DataSource dataSource(){
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/studydb");
        // 개발용
        //dataSource.setUsername("123rhkddms2");
        //dataSource.setPassword("1234");

        // 배포용(AWS)
        dataSource.setUsername("123rhkddms2");
        dataSource.setPassword("Rlarhkddms66@");

        // DBCP2 옵션 설정
        dataSource.setMaxTotal(13); // 최대 연결 풀 크기 설정
        dataSource.setMaxIdle(13);  // 최대 유휴 연결 풀 크기 설정

        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }

}