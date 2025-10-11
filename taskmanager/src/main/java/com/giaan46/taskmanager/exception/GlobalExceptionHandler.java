package com.giaan46.taskmanager.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	// Manejo de errores de validacion (@valid)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, Object>> handleValidationErrors(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();

		ex.getBindingResult().getAllErrors().forEach(error -> {
			String fieldName = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			errors.put(fieldName, message);

		});


	Map<String, Object> body = new HashMap<>();body.put("status",HttpStatus.BAD_REQUEST.value());body.put("errors",errors);

	return new ResponseEntity<>(body,HttpStatus.BAD_REQUEST);

}

// manejo generico de otras excepciones
@ExceptionHandler(Exception.class)
public ResponseEntity<Map<String, Object>> handleGeneralErros(Exception ex) {
	Map<String, Object> body = new HashMap<>();
	body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
	body.put("error", ex.getMessage());

	return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);


}
}
