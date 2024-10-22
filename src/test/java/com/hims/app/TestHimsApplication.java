package com.hims.app;

import org.springframework.boot.SpringApplication;

public class TestHimsApplication {

	public static void main(String[] args) {
		SpringApplication.from(HimsApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
