package org.kamil.exception;

public class PlatformNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PlatformNotFoundException(Integer id) {
		super("Platform with given id("+id+") was not found");
	}
}
