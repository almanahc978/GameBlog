package org.kamil.exception;

public class GameNoFoundException extends RuntimeException {

	public GameNoFoundException(Integer id) {
		super("No game with given ID(" + id + ")" + " was found");
	}
}
