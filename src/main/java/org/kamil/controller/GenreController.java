package org.kamil.controller;

import org.hibernate.id.IntegralDataTypeHolder;
import org.kamil.conversion.GenreModelAssembler;
import org.kamil.model.Genre;
import org.kamil.service.impl.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api", produces = {"application/hall+json"})
public class GenreController {

	private GenreService genreServiceImpl;

	private GenreModelAssembler genreModelAssembler;

	@Autowired
	public GenreController(GenreService genreServiceImpl, GenreModelAssembler genreModelAssembler) {
		this.genreServiceImpl = genreServiceImpl;
		this.genreModelAssembler = genreModelAssembler;
	}

	@GetMapping("/genres/all")
	public CollectionModel<EntityModel<Genre>> getAll(){

		List<EntityModel<Genre>>  genres = genreServiceImpl.getAll().stream().map(genreModelAssembler::toModel).collect(Collectors.toList());

		return CollectionModel.of(genres, WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(GenreController.class).getAll()).withSelfRel());
	}

	@GetMapping("/genres/name/{name}")
	public CollectionModel<EntityModel<Genre>> getByName(@PathVariable String name){
		List<EntityModel<Genre>> genres = genreServiceImpl.getByName(name).stream().map(genreModelAssembler::toModel).collect(Collectors.toList());

		return CollectionModel.of(genres, WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(GenreController.class).getByName(name)).withSelfRel());
	}

	@GetMapping("/genres/id/{id}")
	public EntityModel<Genre> getById(@PathVariable Integer id){
		return genreModelAssembler.toModel(genreServiceImpl.getById(id));
	}

	@PostMapping("/genres/add")
	public ResponseEntity<?> add(@Valid @RequestBody Genre genre){
		EntityModel<Genre> entityModel = genreModelAssembler.toModel(genreServiceImpl.add(genre));

		return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
	}

	@PutMapping("/genres/update/{id}")
	public ResponseEntity<?> update(@Valid @PathVariable Integer id, @RequestBody Genre genre){
		EntityModel<Genre> entityModel = genreModelAssembler.toModel(genreServiceImpl.update(genre, id));

		return  ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
	}

	@DeleteMapping("/genres/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id){
		genreServiceImpl.delete(id);
		return  ResponseEntity.noContent().build();
	}



}
