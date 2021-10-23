package org.kamil.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Game extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotBlank(message = "Field 'Name' must not be blank")
	private String name;

	@Size(min = 10, max = 1000, message = "Field 'Description' must be between 10 and 1000 characters")
	private String description;

	private LocalDate releaseDate;

	@Min(value = 0, message = "Rating must be greater than 0")
	@Max(value = 100, message = "Rating can't be greater than 100")
	private int rating;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "game_genre", joinColumns = @JoinColumn(name = "game_id"), inverseJoinColumns = @JoinColumn(name = "genre_id"))
	private List<Genre> genre = new ArrayList<>();

	@ManyToMany
	@JoinTable(name = "game_platform", joinColumns = @JoinColumn(name = "game_id"), inverseJoinColumns = @JoinColumn(name = "platform_id"))
	private List<Platform> platform = new ArrayList<>();

	@ManyToOne
	@JoinColumn(name = "publisher_name", referencedColumnName = "name")
	private Publisher publisher;

	public Game() {

	}

	public Game(String name) {
		this.name = name;
	}

	public Game(String name, String description, LocalDate releaseDate, int rating) {
		this.name = name;
		this.description = description;
		this.releaseDate = releaseDate;
		this.rating = rating;
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

	public LocalDate getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public List<Genre> getGenre() {
		return genre;
	}

	public void setGenre(List<Genre> genre) {
		this.genre = genre;
	}

	public List<Platform> getPlatform() {
		return platform;
	}

	public void setPlatform(List<Platform> platform) {
		this.platform = platform;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	public void addGenre(Genre genre) {
		this.genre.add(genre);
	}

	public void addPlatform(Platform platform) {
		this.platform.add(platform);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.id, this.name, this.platform, this.description, this.genre, this.publisher,
				this.platform, this.rating, this.releaseDate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Game))
			return false;
		Game game = (Game) obj;
		return Objects.equals(this.id, game.getId()) && Objects.equals(this.name, game.name)
				&& Objects.equals(this.platform, game.getPlatform())
				&& Objects.equals(this.description, game.getDescription())
				&& Objects.equals(this.genre, game.getGenre()) && Objects.equals(this.publisher, game.getPublisher())
				&& Objects.equals(this.platform, game.getPlatform()) && Objects.equals(this.rating, game.getRating())
				&& Objects.equals(this.releaseDate, game.getReleaseDate());
	}

	@Override
	public String toString() {
		return "Game [name=" + name + ", description=" + description + ", releaseDate=" + releaseDate + ", rating="
				+ rating + ", genre=" + genre + ", platform=" + platform + ", publisher=" + publisher + "]";
	}

}
