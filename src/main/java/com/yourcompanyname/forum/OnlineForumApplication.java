package com.yourcompanyname.forum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(modifyOnCreate = false)
public class OnlineForumApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineForumApplication.class, args);
	}

}
