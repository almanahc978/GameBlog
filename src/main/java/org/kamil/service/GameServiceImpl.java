package org.kamil.service;

import java.util.List;
import java.util.stream.Collectors;

import org.kamil.exception.NoDataFoundException;
import org.kamil.exception.NoGameFoundException;
import org.kamil.model.Game;
import org.kamil.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameServiceImpl implements IGameService {

	private GameRepository gameRepository;

	@Autowired
	public GameServiceImpl(GameRepository gameRepository) {
		this.gameRepository = gameRepository;
	}
	
	@Override
	public Game getById(Integer id) {
		return gameRepository.findById(id).orElseThrow(()-> new NoGameFoundException(id));
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
	public List<Game> findGamesByName(String name) {
		List<Game> games = gameRepository.findAllByName(name).stream().collect(Collectors.toList());
		if (games.isEmpty()) {
			throw new NoDataFoundException();
		}
		return games;
	}

	@Override
	public void add(Game game) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Game game) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Game game) {
		// TODO Auto-generated method stub

	}

}
