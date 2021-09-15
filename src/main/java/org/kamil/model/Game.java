package org.kamil.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Game extends BaseEntity {

	private String name;

	@ManyToOne(optional = false, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "genre_id", referencedColumnName = "id")
	private Genre genre;

	@OneToMany(mappedBy = "game")
	private List<GamePublisher> gamePublishers;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

}
