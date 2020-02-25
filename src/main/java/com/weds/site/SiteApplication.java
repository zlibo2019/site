package com.weds.site;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

import com.weds.framework.core.annotation.MyBatisDao;
import com.weds.framework.core.basic.web.RestExceptionHandler;

@EnableScheduling
@SpringBootApplication
@MapperScan(basePackages = "com.weds.site.mapper", annotationClass = MyBatisDao.class)
public class SiteApplication {

    public static void main(String[] args) {
        SpringApplication.run(SiteApplication.class, args);
    }

    @Bean
    public RestExceptionHandler restExceptionHandler() {
        return new RestExceptionHandler();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
