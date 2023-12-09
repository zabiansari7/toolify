package de.srh.toolify;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "de.srh.toolify.repositories")
public class ToolifyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ToolifyApplication.class, args);
	}

}
