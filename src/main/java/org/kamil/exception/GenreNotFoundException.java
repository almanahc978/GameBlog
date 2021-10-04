package org.kamil.exception;

public class GenreNotFoundException extends RuntimeException {

	public GenreNotFoundException(Integer id) {
		super("No genre with given ID(" + id + ")" + " was found");
	}
}
