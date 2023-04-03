package com.zyq;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zyq.dao")
public class ProduceManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProduceManagementApplication.class, args);
    }

}
