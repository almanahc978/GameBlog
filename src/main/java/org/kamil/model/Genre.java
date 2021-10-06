package org.kamil.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Genre extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotBlank(message = "Field 'Name' must not be blank")
	private String name;

	@Size(min = 10, max = 200, message = "Field 'Description' must be between 10 and 200 characters")
	private String description;

	@ManyToMany(mappedBy = "genre")
	private List<Game> games;

	public Genre() {

	}

	public Genre(String name, String description) {
		this.name = name;
		this.description = description;
	}

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Genre [name=" + name + ", description=" + description + ", games=" + games + "]";
	}

}
