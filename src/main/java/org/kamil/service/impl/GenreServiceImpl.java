package org.kamil.service.impl;

import java.util.List;

import org.kamil.exception.GenreNotFoundException;
import org.kamil.exception.NoDataFoundException;
import org.kamil.model.Genre;
import org.kamil.repository.GenreRepository;
import org.kamil.service.IServiceCrud;
import org.kamil.validation.ValidationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenreServiceImpl implements IServiceCrud<Genre> {

	private final GenreRepository genreRepository;
	private final ValidationFacade validationFacade;

	@Autowired
	public GenreServiceImpl(GenreRepository genreRepository, ValidationFacade validationFacade) {
		this.genreRepository = genreRepository;
		this.validationFacade = validationFacade;
	}

	@Override
	public Genre getById(Integer id) {
		return genreRepository.findById(id).orElseThrow(() -> new GenreNotFoundException(id));
	}

	@Override
	public List<Genre> getAll() {
		List<Genre> allGenres = genreRepository.findAll();
		if (allGenres.isEmpty()) {
			throw new NoDataFoundException();
		}
		return allGenres;
	}

	@Override
	public List<Genre> getByName(String name) {
		List<Genre> genres = genreRepository.findByNameContaining(name);
		if (genres.isEmpty()) {
			throw new NoDataFoundException();
		}
		return genres;
	}

	@Override
	public Genre add(Genre genre) {
		validationFacade.validate(genre);
		return genreRepository.save(genre);
	}

	@Override
	public Genre update(Genre newGenre, Integer id) {
		validationFacade.validate(newGenre);
		return genreRepository.findById(id).map(genre -> {
			genre.setName(newGenre.getName());
			genre.setDescription(newGenre.getDescription());
			return genreRepository.save(genre);
		}).orElseGet(() -> {
			newGenre.setId(id);
			return genreRepository.save(newGenre);
		});
	}

	@Override
	public void delete(Integer id) {
		genreRepository.deleteById(id);

	}

}
