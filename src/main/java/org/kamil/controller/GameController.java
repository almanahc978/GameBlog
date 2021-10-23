package org.kamil.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.kamil.conversion.GameModelAssembler;
import org.kamil.model.Game;
import org.kamil.service.impl.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api", produces = {"application/hall+json"})
public class GameController {

	private final GameService gameServiceImpl;
	private final GameModelAssembler gameModelAssembler;

	@Autowired
	public GameController(GameService gameServiceImpl, GameModelAssembler gameModelAssembler) {
		this.gameServiceImpl = gameServiceImpl;
		this.gameModelAssembler = gameModelAssembler;
	}

	@GetMapping(value = "/games/all")
	public CollectionModel<EntityModel<Game>> getAll() {
		List<EntityModel<Game>> games = gameServiceImpl.getAll().stream().map(gameModelAssembler::toModel)
				.collect(Collectors.toList());

		return CollectionModel.of(games,
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(GameController.class).getAll()).withSelfRel());
	}


	@GetMapping(value = "/games/name/{name}")
	public CollectionModel<EntityModel<Game>> getByName(@PathVariable String name) {
		List<EntityModel<Game>> games = gameServiceImpl.getByName(name).stream().map(gameModelAssembler::toModel)
				.collect(Collectors.toList());

		return CollectionModel.of(games,
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(GameController.class).getAll()).withSelfRel());
	}

	@GetMapping("/games/id/{id}")
	public EntityModel<Game> getById(@PathVariable Integer id) {
		return gameModelAssembler.toModel(gameServiceImpl.getById(id));
	}

	@PostMapping("/games/add")
	public ResponseEntity<?> add(@Valid @RequestBody Game game) {

		EntityModel<Game> entityModel = gameModelAssembler.toModel(gameServiceImpl.add(game));
		return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
	}

	@PutMapping("/games/update/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Game game, @PathVariable Integer id) {
		EntityModel<Game> entityModel = gameModelAssembler.toModel(gameServiceImpl.update(game, id));

		return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
	}

	@DeleteMapping("/games/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		gameServiceImpl.delete(id);
		return ResponseEntity.noContent().build();
	}
}
