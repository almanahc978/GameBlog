package org.kamil.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class GamePublisher extends BaseEntity {


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "game_id", referencedColumnName = "id")
	private Game game;

	@ManyToOne
	@JoinColumn(name = "publisher_id", referencedColumnName = "id")
	private Publisher publisher;

	@OneToMany(mappedBy = "gamePublisher")
	private List<GamePlatform> gamePlatforms;


	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	public List<GamePlatform> getGamePlatforms() {
		return gamePlatforms;
	}

	public void setGamePlatforms(List<GamePlatform> gamePlatforms) {
		this.gamePlatforms = gamePlatforms;
	}

}
