package de.srh.toolify.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import de.srh.toolify.dto.ToolifyResponse;

@ControllerAdvice
public class PurchaseExceptionAdvice {
	
	@ResponseBody
	@ExceptionHandler(PurchaseException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	ToolifyResponse purchaseBadRequestExceptionHandler(PurchaseException ex) {
		return new ToolifyResponse(ex.getMessage(), 400, HttpStatus.BAD_REQUEST);
	}
	

}
