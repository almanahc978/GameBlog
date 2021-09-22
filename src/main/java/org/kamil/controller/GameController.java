package org.kamil.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.kamil.model.Game;
import org.kamil.service.GameServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class GameController {

	private final GameServiceImpl gameServiceImpl;

	@Autowired
	public GameController(GameServiceImpl gameServiceImpl) {
		this.gameServiceImpl = gameServiceImpl;
	}

	@GetMapping("/games")
	public CollectionModel<EntityModel<Game>> getAll() {
		List<EntityModel<Game>> games = gameServiceImpl.getAll().stream().map(game -> EntityModel.of(game,
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(GameController.class).getById(game.getId()))
						.withSelfRel(),
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(GameController.class).getAll()).withRel("games")))
				.collect(Collectors.toList());

		return CollectionModel.of(games,
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(GameController.class).getAll()).withSelfRel());
	}

	@GetMapping("/games/{name}")
	public List<Game> getByName(@PathVariable String name) {
		return gameServiceImpl.getByName(name);
	}

	@GetMapping("/games/id/{id}")
	public EntityModel<Game> getById(@PathVariable Integer id) {
		return EntityModel.of(gameServiceImpl.getById(id),
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(GameController.class).getById(id)).withSelfRel(),
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(GameController.class).getAll()).withRel("games"));
	}

	@PostMapping("/games/add")
	public Game add(@Valid @RequestBody Game game) {
		return gameServiceImpl.add(game);
	}

	@PutMapping("/games/update/{id}")
	public Game update(@Valid @RequestBody Game game, @PathVariable Integer id) {
		return gameServiceImpl.update(game, id);
	}

	@DeleteMapping("/games/delete/{id}")
	public void delete(@PathVariable Integer id) {
		gameServiceImpl.delete(id);
	}
}
