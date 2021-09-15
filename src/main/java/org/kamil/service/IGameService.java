package org.kamil.service;

import java.util.List;

import org.kamil.model.Game;

public interface IGameService {

	Game getById(Integer id);
	List<Game> getAll();
	List<Game> findGamesByName(String name);
	void add(Game game);
	void update(Game game);
	void delete(Game game);
}
