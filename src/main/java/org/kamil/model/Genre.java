package org.kamil.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class Genre extends BaseEntity {

	private String name;

	@OneToMany(mappedBy = "genre", fetch = FetchType.LAZY)
	private List<Game> games;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Game> getGames() {
		return games;
	}

	public void setGames(List<Game> games) {
		this.games = games;
	}

}
