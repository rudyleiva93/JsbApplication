package com.example.jsb;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JsbApplication {

	public static void main(String[] args) {
		SpringApplication.run(JsbApplication.class, args);
	}

}
