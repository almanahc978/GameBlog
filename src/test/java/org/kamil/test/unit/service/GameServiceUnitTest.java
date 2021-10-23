package org.kamil.test.unit.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.kamil.exception.GameNoFoundException;
import org.kamil.exception.NoDataFoundException;
import org.kamil.model.Game;
import org.kamil.repository.GameRepository;
import org.kamil.service.impl.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;

import static org.junit.Assert.assertThrows;

@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:application-test.properties")
@SpringBootTest
@ActiveProfiles("test")
class GameServiceUnitTest implements IGameServiceCrud{

	@Autowired
	private GameService gameServiceImpl;
	
	@Autowired
	private GameRepository gameRepository;

	@BeforeEach
	public void config() {
		gameRepository.deleteAll();
	}

	@Test
	@DisplayName("Test GameService:getById(Integer id)")
	public void  getById() {
		Game game = new Game("This is game no 1");
		game = gameRepository.save(game);
		
		Assertions.assertEquals(game.getName(), gameServiceImpl.getById(game.getId()).getName());
	}

	@Test
	@DisplayName("Test GameServiceImpl:getById(Integer id) throws GameNotFoundException")
	public void getByIdWithException() {
		assertThrows(GameNoFoundException.class, ()-> gameServiceImpl.getById(1));
	}
	
	@Test
	@DisplayName("Test GameServiceImpl:getAll()")
	public void getAll() {
		Game game1 = new Game("This is game no 1");
		Game game2 = new Game("This is game no 2");
		Game game3 = new Game("This is game no 3");
		
		gameRepository.save(game1);
		gameRepository.save(game2);
		gameRepository.save(game3);
		
		Assertions.assertEquals(gameRepository.count(), gameServiceImpl.getAll().size());
	}
	
	@Test
	@DisplayName("Test GameServiceImpl:getAll() throws NoDataFoundException")
	public void getAllWithException() {
		assertThrows(NoDataFoundException.class, ()-> gameServiceImpl.getAll());
	}
	
	@Test
	@DisplayName("Test GameServiceImpl:getByName(String name)")
	public void getByName() {
		Game game = new Game("This is new game");
		game = gameRepository.save(game);
		Assertions.assertEquals(gameRepository.count(), gameServiceImpl.getByName("game").size());
		Assertions.assertEquals(game.getName(), gameServiceImpl.getByName("game").get(0).getName());
	}
	
	@Test
	@DisplayName("Test GameServiceImpl:getByName(String name) throws NoDataFoundException")
	public void getByNameWithException() {
		assertThrows(NoDataFoundException.class, ()-> gameServiceImpl.getByName("something"));
	}
	
	@Test
	@DisplayName("Test GameServiceImpl:add(Game game)")
	public void add() {
		gameServiceImpl.add(new Game("This in new game"));
		Assertions.assertEquals(1, gameRepository.count());
	}
	
	@Test
	@DisplayName("Test GameServiceImpl:add(Game game) throws ConstraintViolationException")
	public void addWithException() {
		assertThrows(ConstraintViolationException.class, ()-> gameServiceImpl.add(new Game()));
	}
	
	@Test
	@DisplayName("Test GameServiceImpl:update(Game game)")
	public void update() {
		Game game = new Game("Game name before update");
		game = gameRepository.save(game);
		game.setName("Game name after update");
		game = gameServiceImpl.update(game, game.getId());
	
		Assertions.assertEquals(game.getName(), gameServiceImpl.getById(game.getId()).getName());
		
	}
	
	@Test
	@DisplayName("Test GameServiceImpl:delete(Integer id)")
	public void delete() {
		Game game1 = new Game("game no 1");
		Game game2 = new Game("game no 2");
		game1 = gameRepository.save(game1);
		game2 = gameRepository.save(game2);
		
		Assertions.assertEquals(2, gameRepository.count());
		
		gameServiceImpl.delete(game1.getId());
		
		Assertions.assertEquals(1, gameRepository.count());

		gameServiceImpl.delete(game2.getId());

		Assertions.assertEquals(0, gameRepository.count());

	}
	
}
