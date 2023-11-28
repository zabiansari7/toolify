package de.srh.toolify.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import de.srh.toolify.dto.ToolifyResponse;

@ControllerAdvice
public class UserExceptionAdvice {
	
	@ResponseBody
	@ExceptionHandler(UserException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	ToolifyResponse userBadRequestExceptionHandler(UserException ex) {
		return new ToolifyResponse(ex.getCause().getLocalizedMessage(), 400, HttpStatus.BAD_REQUEST);
	}
}
