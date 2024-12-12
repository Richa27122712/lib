package library.Managemnt.library.Exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MaxBookIssuedException.class)
	public ResponseEntity<Map<String,Object>> MaxBookIssuedExceptionHandler(MaxBookIssuedException ex){
		Map<String,Object> map=new HashMap<>();
		map.put("message", ex.getMessage());
		map.put("status", HttpStatus.BAD_REQUEST);
		map.put("name", "Richa");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
	}
	@ExceptionHandler(BookNotFoundException.class)
	public ResponseEntity<Map<String,Object>> BookNotFoundExceptionHandler(BookNotFoundException ex){
		Map<String,Object> map=new HashMap<>();
		map.put("message", ex.getMessage());
		map.put("status",HttpStatus.NOT_FOUND);
		return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
	}
	@ExceptionHandler(UserAlreadyHasBookException.class)
	public ResponseEntity<Map<String,Object>> UserAlreadyHasBookExceptionHandler(UserAlreadyHasBookException ex){
		Map<String,Object> map=new HashMap<>();
		map.put("message",ex.getMessage());
		map.put("status", HttpStatus.BAD_REQUEST);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
	}
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<Map<String,Object>> RuntimeExceptionHandler(RuntimeException ex){
		Map<String,Object> map=new HashMap<>();
		map.put("message", ex.getMessage());
		map.put("status", HttpStatus.NOT_FOUND);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
		
	}
}
