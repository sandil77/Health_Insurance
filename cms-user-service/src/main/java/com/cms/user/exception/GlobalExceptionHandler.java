package com.cms.user.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cms.user.dto.CommonApiResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // ❌ Handle validation errors
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
//        Map<String, String> errors = new HashMap<>();
//
//        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
//            errors.put(error.getField(), error.getDefaultMessage());
//        }
//
//        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
//    }

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<CommonApiResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
	    StringBuilder errorMsg = new StringBuilder();

	    ex.getBindingResult().getFieldErrors().forEach(error -> {
	        errorMsg.append(error.getField())
	                .append(": ")
	                .append(error.getDefaultMessage())
	                .append("; ");
	    });

	    CommonApiResponse response = CommonApiResponse.builder()
	            .responseMessage("Validation failed: " + errorMsg.toString())
	            .isSuccess(false)
	            .status(HttpStatus.BAD_REQUEST)
	            .build();

	    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}



    // ❌ Handle custom user not found exception
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<CommonApiResponse> handleUserNotFoundException(UserNotFoundException ex) {
        String responseMessage = ex.getMessage();

        CommonApiResponse apiResponse = CommonApiResponse.builder()
                .responseMessage(responseMessage)
                .isSuccess(false)
                .status(HttpStatus.NOT_FOUND)
                .build();

        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }
    
    
}
