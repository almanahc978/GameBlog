package org.kamil.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.websocket.Session;

import org.kamil.exception.NoDataFoundException;
import org.kamil.exception.GameNoFoundException;
import org.kamil.model.Game;
import org.kamil.repository.GameRepository;
import org.kamil.service.IServiceCrud;
import org.kamil.validation.ValidationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameServiceImpl implements IServiceCrud<Game> {

	private final GameRepository gameRepository;

	private final ValidationFacade validationFacade;

	@Autowired
	public GameServiceImpl(GameRepository gameRepository, ValidationFacade validationFacade) {
		this.gameRepository = gameRepository;
		this.validationFacade = validationFacade;
	}

	@Override
	public Game getById(Integer id) {
		return gameRepository.findById(id).orElseThrow(() -> new GameNoFoundException(id));
	}

	@Override
	public List<Game> getAll() {
		List<Game> allGames = gameRepository.findAll();
		if (allGames.isEmpty()) {
			throw new NoDataFoundException();
		}
		return allGames;
	}

	@Override
	public List<Game> getByName(String name) {
		List<Game> games = gameRepository.findByNameContaining(name);
		if (games.isEmpty()) {
			throw new NoDataFoundException();
		}
		return games;
	}

	@Override
	public Game add(Game game) {
		validationFacade.validate(game);
		return gameRepository.save(game);
	}

	@Override
	public Game update(Game newGame, Integer id) {
		validationFacade.validate(newGame);

		return gameRepository.findById(id).map(game -> {
			game.setName(newGame.getName());
			game.setDescription(newGame.getDescription());
			game.setReleaseDate(newGame.getReleaseDate());
			game.setRating(newGame.getRating());
			game.setPublisher(newGame.getPublisher());
			return gameRepository.save(game);
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
