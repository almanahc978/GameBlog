package org.kamil.repository;

import java.util.List;

import org.kamil.model.Platform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlatformRepository extends JpaRepository<Platform, Integer> {

	List<Platform> findByName(String name);
}
