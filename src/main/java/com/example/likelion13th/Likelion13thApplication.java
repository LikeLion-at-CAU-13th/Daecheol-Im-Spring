package com.example.likelion13th;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing // 추가
public class Likelion13thApplication {

	public static void main(String[] args) {
		SpringApplication.run(Likelion13thApplication.class, args);
	}

}
