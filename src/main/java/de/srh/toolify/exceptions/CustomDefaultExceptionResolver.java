package de.srh.toolify.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import de.srh.toolify.dto.ToolifyResponse;

@Component
@ControllerAdvice
public class CustomDefaultExceptionResolver {
	
	@ResponseBody
	@ExceptionHandler(UnsatisfiedServletRequestParameterException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	ToolifyResponse defaultExceptionHandler(UnsatisfiedServletRequestParameterException ex) {
		return new ToolifyResponse(ex.getMessage(), 400, HttpStatus.BAD_REQUEST);
	}
}
