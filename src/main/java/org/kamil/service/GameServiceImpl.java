package org.kamil.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.websocket.Session;

import org.kamil.exception.NoDataFoundException;
import org.kamil.exception.NoGameFoundException;
import org.kamil.model.Game;
import org.kamil.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameServiceImpl implements IGameService {

	private final GameRepository gameRepository;

	@Autowired
	public GameServiceImpl(GameRepository gameRepository) {
		this.gameRepository = gameRepository;
	}

	@Override
	public Game getById(Integer id) {
		return gameRepository.findById(id).orElseThrow(() -> new NoGameFoundException(id));
	}

	@Override
	public List<Game> getAll() {
		List<Game> allGames = gameRepository.findAll().stream().collect(Collectors.toList());
		if (allGames.isEmpty()) {
			throw new NoDataFoundException();
		}
		return allGames;
	}

	@Override
	public List<Game> getByName(String name) {
		List<Game> games = gameRepository.findAllByName(name).stream().collect(Collectors.toList());
		if (games.isEmpty()) {
			throw new NoDataFoundException();
		}
		return games;
	}

	@Override
	public Game add(Game game) {
		return gameRepository.save(game);
	}

	@Override
	public Game update(Game newGame, Integer id) {
		return gameRepository.findById(id).map(game -> {
			game.setName(newGame.getName());
			game.setDescription(newGame.getDescription());
			game.setReleaseDate(newGame.getReleaseDate());
			game.setRating(newGame.getRating());
			game.setPublisher(newGame.getPublisher());
			return gameRepository.save(newGame);
		}).orElseGet(() -> {
			newGame.setId(id);
			return gameRepository.save(newGame);
		});

	}

	@Override
	public void delete(Integer id) {
		gameRepository.deleteById(id);
	}

}
