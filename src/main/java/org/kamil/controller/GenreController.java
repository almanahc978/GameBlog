package org.kamil.controller;

import org.kamil.service.impl.GenreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class GenreController {

	private GenreServiceImpl genreServiceImpl;

	@Autowired
	public GenreController(GenreServiceImpl genreServiceImpl) {
		this.genreServiceImpl = genreServiceImpl;
	}
}
