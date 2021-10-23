package org.kamil.conversion;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.kamil.controller.GameController;
import org.kamil.model.Game;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class GameModelAssembler implements RepresentationModelAssembler<Game, EntityModel<Game>> {

	@Override
	public EntityModel<Game> toModel(Game entity) {
		return EntityModel.of(entity, linkTo(methodOn(GameController.class).getById(entity.getId())).withSelfRel(),
				linkTo(methodOn(GameController.class).getAll()).withRel("games"));
	}
}
