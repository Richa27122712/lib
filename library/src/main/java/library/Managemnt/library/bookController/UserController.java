package library.Managemnt.library.bookController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import library.Managemnt.library.bookService.UserService;
import library.Managemnt.library.entity.User;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
private	UserService userService;
	
	@PostMapping("/save")
	public ResponseEntity<User> SaveDetail(@RequestBody User user) {
		User usersaved=userService.SaveUser(user);
		return new ResponseEntity<>(usersaved,HttpStatus.CREATED);
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<User>> GetAllDetails(){
		List<User> users=userService.getAll();
		return new ResponseEntity<>(users,HttpStatus.OK);
	}
}
