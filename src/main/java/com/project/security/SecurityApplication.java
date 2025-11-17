package com.project.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableJpaAuditing
public class SecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityApplication.class, args);
		// BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        // String rawPassword = "1234";
        // String encodedPassword = encoder.encode(rawPassword);
        // System.out.println("test 용 pwd:"+ encodedPassword);
    
	}

}
