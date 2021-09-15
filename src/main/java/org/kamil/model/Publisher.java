package org.kamil.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Publisher extends BaseEntity {

	private String name;

	@OneToMany(mappedBy = "publisher")
	private List<GamePublisher> gamePublishers;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<GamePublisher> getGamePublishers() {
		return gamePublishers;
	}

	public void setGamePublishers(List<GamePublisher> gamePublishers) {
		this.gamePublishers = gamePublishers;
	}

}
