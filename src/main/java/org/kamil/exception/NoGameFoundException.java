package org.kamil.exception;

public class NoGameFoundException extends RuntimeException {

	public NoGameFoundException(Integer id) {
		super("No game with given ID(" + id + ")" + " was found");
	}
}
