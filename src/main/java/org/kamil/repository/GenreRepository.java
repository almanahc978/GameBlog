package org.kamil.repository;

import java.util.List;

import org.kamil.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Integer> {

	List<Genre> findByNameContaining(String names);
}
