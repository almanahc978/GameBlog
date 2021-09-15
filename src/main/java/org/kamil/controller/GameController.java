package org.kamil.controller;

import java.util.List;

import org.kamil.model.Game;
import org.kamil.service.GameServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class GameController {

	private GameServiceImpl gameServiceImpl;

	@Autowired
	public GameController(GameServiceImpl gameServiceImpl) {
		this.gameServiceImpl = gameServiceImpl;
	}

	@GetMapping("/games")
	public List<Game> getAllGames() {
		return gameServiceImpl.getAll();
	}

	@GetMapping("/games/{name}")
	public List<Game> getGamesByName(@PathVariable String name) {
		return gameServiceImpl.findGamesByName(name);
	}

	@GetMapping("/games/id/{id}")
	public Game getGameById(@PathVariable Integer id) {
		return gameServiceImpl.getById(id);
	}
}
