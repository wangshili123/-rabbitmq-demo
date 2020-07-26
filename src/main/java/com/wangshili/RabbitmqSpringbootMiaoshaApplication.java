package com.wangshili;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@MapperScan("com.wangshili.dao")
@SpringBootApplication
public class RabbitmqSpringbootMiaoshaApplication {

	public static void main(String[] args) {
		SpringApplication.run(RabbitmqSpringbootMiaoshaApplication.class, args);
	}

}
