package org.kamil.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;

@Entity
public class Platform extends BaseEntity {

	@NotBlank(message = "Field 'Name' must not be blank")
	private String name;

	private String description;

	@ManyToMany(mappedBy = "platform")
	private List<Game> games;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Game> getGames() {
		return games;
	}

	public void setGames(List<Game> games) {
		this.games = games;
	}
	

}
