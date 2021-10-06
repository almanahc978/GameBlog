package org.kamil.exception;

public class PublisherNotFoundException extends RuntimeException {

	public PublisherNotFoundException(Integer id) {
		super("Publisher with given id("+id+") was not found");
	}
}
