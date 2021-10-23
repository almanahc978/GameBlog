package org.kamil.test.unit.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.kamil.model.Genre;
import org.kamil.repository.GenreRepository;
import org.kamil.service.impl.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:application-test.properties")
@SpringBootTest
@ActiveProfiles("test")
public class GenreServiceUnitTest implements IGameServiceCrud{

    @Autowired
    private GenreService genreService;

    @Autowired
    private GenreRepository genreRepository;

    @Override
    @BeforeEach
    public void config() {
        genreRepository.deleteAll();
    }

    @Override
    @Test
    @DisplayName("GenreService:getById(Integer id)")
    public void getById() {
        Genre genre = new Genre("Genre no 1");
        genre = genreRepository.save(genre);

        Assertions.assertEquals(genre.getName(), genreService.getById(genre.getId()).getName());
    }

    @Override
    @Test
    public void getByIdWithException() {

    }

    @Override
    @Test
    public void getAll() {

    }

    @Override
    @Test
    public void getAllWithException() {

    }

    @Override
    @Test
    public void getByName() {

    }

    @Override
    @Test
    public void getByNameWithException() {

    }

    @Override
    @Test
    public void add() {

    }

    @Override
    @Test
    public void addWithException() {

    }

    @Override
    @Test
    public void update() {

    }

    @Override
    @Test
    public void delete() {

    }
}
