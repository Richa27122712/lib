package library.Managemnt.library.bookService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import library.Managemnt.library.Repository.UserRepository;
import library.Managemnt.library.entity.User;

@Service
public class UserServiceImpl implements UserService
{

	@Autowired
	private UserRepository userRepository;
	
	public User SaveUser(User user) {
		return userRepository.save(user);
		
	}
	
	public List<User> getAll(){
		return userRepository.findAll();
	}
}
