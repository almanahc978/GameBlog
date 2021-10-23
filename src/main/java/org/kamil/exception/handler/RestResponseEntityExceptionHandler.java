package org.kamil.exception.handler;

import java.net.http.HttpHeaders;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.kamil.exception.NoDataFoundException;
import org.kamil.exception.GameNoFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	/*
	 * * When no data was found
	 */

	@ExceptionHandler(value = { NoDataFoundException.class })
	protected ResponseEntity<Object> handleNoDataFoundException(NoDataFoundException ex, WebRequest request) {
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timeStamp", LocalDateTime.now());
		body.put("message", "No data found");

		return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
	}

	/*
	 * * When no game was found
	 */

	@ExceptionHandler(value = { GameNoFoundException.class })
	protected ResponseEntity<Object> handleNoGameFoundException(GameNoFoundException ex, WebRequest request) {
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timeStamp", LocalDateTime.now());
		body.put("message", "No game with given ID was found");

		return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
	}

	/*
	 * * When validation on argument with @Valid fails
	 */
	
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timeStamp", LocalDateTime.now());
		body.put("status", status.value());

		List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(x -> x.getDefaultMessage())
				.collect(Collectors.toList());

		body.put("errors", errors);

		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}

}
