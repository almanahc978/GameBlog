package org.kamil.model;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class GamePlatform extends BaseEntity {
	
	private LocalDate releaseDate;
	
	@ManyToOne
	@JoinColumn(name = "gamePublisher_id", referencedColumnName = "id")
	private GamePublisher gamePublisher;
	
	@ManyToOne
	@JoinColumn(name = "platform_id", referencedColumnName = "id")
	private Platform platform;


	public LocalDate getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}

	public GamePublisher getGamePublisher() {
		return gamePublisher;
	}

	public void setGamePublisher(GamePublisher gamePublisher) {
		this.gamePublisher = gamePublisher;
	}

	public Platform getPlatform() {
		return platform;
	}

	public void setPlatform(Platform platform) {
		this.platform = platform;
	}
	
	

}
