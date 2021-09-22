package org.kamil.dataload;

import org.kamil.model.Game;
import org.kamil.repository.GameRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {

	private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

	@Bean
	CommandLineRunner innitDatabase(GameRepository gameRepository) {
		return args -> {
			log.info("Preloading " + gameRepository.save(new Game("Red Dead Redemption 2")));
			log.info("PreLoading " + gameRepository.save(new Game("God of War")));
		};
	}
}
