package com.springboot.Intelligent.Banking.Platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class IntelligentBankingPlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(IntelligentBankingPlatformApplication.class, args);
	}

}
