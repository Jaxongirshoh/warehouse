package dev.wisespirit.warehouse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * This simple class demonstrate Spring boot main class
 * @version 0.1
 * @author wisespirit
 */

@SpringBootApplication
@EnableJpaRepositories
@EnableJpaAuditing
public class WarehouseApplication {

	public static void main(String[] args) {
		SpringApplication.run(WarehouseApplication.class, args);
	}

}
