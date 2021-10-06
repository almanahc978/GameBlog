package org.kamil.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Publisher extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotBlank(message = "Field 'Name' must not be blank")
	private String name;

	@Size(min = 10, max = 200, message = "Field 'Description' must be between 10 and 200 characters")
	private String description;

	@OneToMany(mappedBy = "publisher")
	private List<Game> games;

	public Publisher() {

	}

	public Publisher(String name, String description) {
		this.name = name;
		this.description = description;
	}

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

	@Override
	public String toString() {
		return "Publisher [name=" + name + ", description=" + description + ", games=" + games + "]";
	}


}
