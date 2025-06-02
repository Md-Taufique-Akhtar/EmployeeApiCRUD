package com.Scalive.Exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(value=NoSuchEmpExistException.class)
	public ResponseEntity<ErrorResponse> handleNoSuchEmpExistException(NoSuchEmpExistException ex) {
		ErrorResponse error=new ErrorResponse(HttpStatus.NOT_FOUND.value(),ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
						
	 @ExceptionHandler(value=EmpAlreadyExistsException.class)
	    @ResponseStatus(HttpStatus.CONFLICT)
	    @ResponseBody
	    public ErrorResponse handleEmpAlreadyExistsException(EmpAlreadyExistsException ex) {
	    	ErrorResponse error=new ErrorResponse(HttpStatus.CONFLICT.value(),ex.getMessage());
	    	return error;
	    }
	 // this Exception for Validation 
	 //we use map (fieldName , errorMessage)
	 @ExceptionHandler(value=MethodArgumentNotValidException.class)
		public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException ex) {
		Map<String, String>	 errors=new HashMap<>();
		//bindingResult have a error list
		BindingResult bindingResult=ex.getBindingResult();
		List<FieldError> errorList=bindingResult.getFieldErrors();
		for(FieldError error :errorList) {
			errors.put(error.getField(), error.getDefaultMessage());
			}
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
		}
	 //error code 500
	 @ExceptionHandler(value=Exception.class)
		public ResponseEntity<ErrorResponse> handleException(Exception ex) {
			ErrorResponse error=new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),ex.getMessage());
			return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		}
}
