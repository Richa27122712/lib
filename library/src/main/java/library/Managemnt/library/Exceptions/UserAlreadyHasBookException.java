package library.Managemnt.library.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserAlreadyHasBookException extends RuntimeException {
public	UserAlreadyHasBookException(String message) {
	super(message);
}
}
