package org.kamil.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Platform extends BaseEntity {

	private String name;

	@OneToMany(mappedBy = "platform")
	private List<GamePlatform> gamePlatforms;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
