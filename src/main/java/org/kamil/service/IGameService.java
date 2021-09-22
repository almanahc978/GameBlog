package org.kamil.service;

import java.util.List;

import org.kamil.model.Game;

public interface IGameService {

	Game getById(Integer id);
	List<Game> getAll();
	List<Game> getByName(String name);
	Game add(Game game);
	Game update(Game newGame, Integer id);
	void delete(Integer id);
}
