package com.bae.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "This user does not exist")
public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -2591687720244290021L;

}
