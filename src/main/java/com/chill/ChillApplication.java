package com.chill;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ServletComponentScan
@MapperScan("com.chill.mapper")
@EnableTransactionManagement
public class ChillApplication {
    public static void main(String[] args) {
        SpringApplication.run(ChillApplication.class,args);
    }
}
