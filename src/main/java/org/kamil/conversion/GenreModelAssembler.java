package org.kamil.conversion;

import org.kamil.controller.GenreController;
import org.kamil.model.Genre;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class GenreModelAssembler implements RepresentationModelAssembler<Genre, EntityModel<Genre>> {

    @Override
    public EntityModel<Genre> toModel(Genre entity) {
        return EntityModel.of(entity, linkTo(methodOn(GenreController.class).getById(entity.getId())).withSelfRel(),
                linkTo(methodOn(GenreController.class).getAll()).withRel("games"));
    }
}
