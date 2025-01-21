package com.example.crudapptest;

import org.springframework.boot.SpringApplication;

public class TestCrudapptestApplication {

	public static void main(String[] args) {
		SpringApplication.from(CrudapptestApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
