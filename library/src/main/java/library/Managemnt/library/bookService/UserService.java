package library.Managemnt.library.bookService;

import java.util.List;

import library.Managemnt.library.entity.User;

public interface UserService {

	User SaveUser(User user);
	 List<User> getAll();
}
