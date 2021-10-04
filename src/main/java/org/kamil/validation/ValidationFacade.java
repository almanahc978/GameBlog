package org.kamil.validation;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import org.springframework.stereotype.Component;

@Component
public class ValidationFacade {

	private final Validator validator;

	public ValidationFacade(Validator validator) {
		this.validator = validator;
	}

	public <T> void validate(T object, Class<?>... groups) {
		Set<ConstraintViolation<T>> violations = validator.validate(object, groups);
		if (!violations.isEmpty()) {
			throw new ConstraintViolationException(violations);
		}
	}
}
