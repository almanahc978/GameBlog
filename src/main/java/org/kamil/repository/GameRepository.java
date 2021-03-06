package org.kamil.repository;

import java.util.List;

import org.kamil.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {

	List<Game> findByNameContaining(String name);

}
