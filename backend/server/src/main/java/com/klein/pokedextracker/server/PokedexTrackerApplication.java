package com.klein.pokedextracker.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.klein.pokedextracker"})
@EnableJpaRepositories(basePackages = {"com.klein.pokedextracker"})
@EntityScan(basePackages = {"com.klein.pokedextracker"})
public class PokedexTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PokedexTrackerApplication.class, args);
	}

}
