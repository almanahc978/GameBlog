package org.kamil.test.unit.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.kamil.exception.GameNoFoundException;
import org.kamil.exception.NoDataFoundException;
import org.kamil.model.Game;
import org.kamil.repository.GameRepository;
import org.kamil.service.impl.GameServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:application-test.properties")
@SpringBootTest
@ActiveProfiles("test")
public class GameServiceUnitTest {

	@Autowired
	private GameServiceImpl gameServiceImpl;
	
	@Autowired
	private GameRepository gameRepository;
	
	@BeforeEach
	void config() {
		gameRepository.deleteAll();
	}
	
	@Test
	@DisplayName("Test GameServiceImpl:getById(Integer id)")
	void  getById() {
		Game game = new Game("This is game no 1");
		
		assertEquals(gameRepository.save(game).getName(), gameServiceImpl.getById(1).getName());
	}
	
	@Test
	@DisplayName("Test GameServiceImpl:getById(Integer id) throws GameNotFoundException")
	void getByIdWithException() {
		assertThrows(GameNoFoundException.class, ()->{
			gameServiceImpl.getById(1);
		});
	}
	
	@Test
	@DisplayName("Test GameServiceImpl:getAll()")
	void getAll() {
		Game game1 = new Game("game1");
		Game game2 = new Game("game2");
		
		gameRepository.save(game1);
		gameRepository.save(game2);
		
		assertEquals(2, gameServiceImpl.getAll().size());
	}
	
	@Test
	@DisplayName("Test GameServiceImpl:getAll() throws NoDataFoundException")
	void getAllWithException() {
		assertThrows(NoDataFoundException.class, ()->{
			gameServiceImpl.getAll();
		});
	}
	
	@Test
	@DisplayName("Test GameServiceImpl:getByName(String name)")
	void getByName() {
		Game game = new Game("This is new game");
		game = gameRepository.save(game);
		assertEquals(1, gameServiceImpl.getByName("game").size());
		assertEquals(gameServiceImpl.getByName("game").get(0).getName(), game.getName());
	}
	
	@Test
	@DisplayName("Test GameServiceImpl:getByName(String name) throws NoDataFoundException")
	void getByNameWithException() {
		assertThrows(NoDataFoundException.class, ()->{
			gameServiceImpl.getByName("something");
		});
	}
	
	@Test
	@DisplayName("Test GameServiceImpl:add(Game game)")
	void add() {
		gameServiceImpl.add(new Game("game"));
		assertEquals(gameRepository.count(), gameServiceImpl.getAll().size());
	}
	
	@Test
	@DisplayName("Test GameServiceImpl:add(Game game) throws ConstraintViolationException")
	void addWithException() {
		assertThrows(ConstraintViolationException.class, ()->{
			gameServiceImpl.add(new Game());
		});
	}
	
	@Test
	@DisplayName("Test GameServiceImpl:update(Game game)")
	void update() {
		Game game = new Game("Game before update");
		game = gameRepository.save(game);
		game.setName("Game after update");
		game = gameServiceImpl.update(game, game.getId());
	
		assertEquals(game.getName(), gameServiceImpl.getById(1).getName());
		
	}
	
	@Test
	@DisplayName("Test GameServiceImpl:delete(Integer id)")
	void delete() {
		Game game1 = new Game("game no 1");
		Game game2 = new Game("game no 2");
		game1 = gameRepository.save(game1);
		game2 = gameRepository.save(game2);
		
		assertEquals(gameRepository.count(), 2);
		
		gameServiceImpl.delete(1);
		
		assertEquals(gameRepository.count(), 1);
	}
	
}
