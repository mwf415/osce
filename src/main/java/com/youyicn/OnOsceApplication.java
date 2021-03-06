package com.youyicn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan(basePackages = "com.youyicn.mapper")
@EnableScheduling
public class OnOsceApplication {
	public static void main(String[] args) {
		SpringApplication.run(OnOsceApplication.class, args);
	}
}
