package com.altice.alticci;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class AlticciApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlticciApplication.class, args);
	}

	@Bean
	public Map<Long, BigDecimal> mapAlticciValues() {
		return new HashMap<>();
	}

}
