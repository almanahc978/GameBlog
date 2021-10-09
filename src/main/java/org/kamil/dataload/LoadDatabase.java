package org.kamil.dataload;

import java.time.LocalDate;

import org.kamil.model.Game;
import org.kamil.model.Genre;
import org.kamil.model.Platform;
import org.kamil.model.Publisher;
import org.kamil.repository.GameRepository;
import org.kamil.repository.GenreRepository;
import org.kamil.repository.PlatformRepository;
import org.kamil.repository.PublisherRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("!test")
public class LoadDatabase {

	private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

	@Bean
	CommandLineRunner innitDatabase(GameRepository gameRepository, PublisherRepository publisherRepository,
			GenreRepository genreRepository, PlatformRepository platformRepository) {
		
		Game game1 = new Game("Red Dead Redemption 2", "Game number 1", LocalDate.now(), 100);
		Game game2 = new Game("The Last of Us", "Game number 2", LocalDate.now(), 95);
		Game game3 = new Game("Battlefield 2048", "Game number 3", LocalDate.now(), 79);
		
		Genre genre1 = new Genre("Action", "genre description");
		Genre genre2 = new Genre("Adventure", "genre description");
		Genre genre3 = new Genre("Horror", "genre description");
		
		Publisher publisher1 = new Publisher("Rockstar", "publisher description");
		Publisher publisher2 = new Publisher("Naughty Dogs", "publisher description");
		Publisher publisher3 = new Publisher("Ea", "publisher description");
		
		Platform platform1 = new Platform("PS4", "game description");
		Platform platform2 = new Platform("PC", "game description");
		
		game1.addGenre(genre1);
		game1.addPlatform(platform1);
		game1.addPlatform(platform2);
		game1.setPublisher(publisher1);
		
		game2.addGenre(genre1);
		game2.addGenre(genre2);
		game2.addGenre(genre3);
		game2.addPlatform(platform1);
		game2.setPublisher(publisher2);
		
		game3.addGenre(genre1);
		game3.addPlatform(platform2);
		game3.setPublisher(publisher1);
		
		return args -> {

			log.info("Preloading " + genreRepository.save(genre1));	
			log.info("Preloading " + genreRepository.save(genre2));
			log.info("Preloading " + genreRepository.save(genre3));
			
			log.info("Preloading " + publisherRepository.save(publisher1));
			log.info("Preloading " + publisherRepository.save(publisher2));
			log.info("Preloading " + publisherRepository.save(publisher3));
			
			log.info("Preloading " + platformRepository.save(platform1));
			log.info("Preloading " + platformRepository.save(platform2));

			log.info("Preloading " + gameRepository.save(game1));
			log.info("Preloading " + gameRepository.save(game2));
			log.info("Preloading " + gameRepository.save(game3));
		};
	}
}
