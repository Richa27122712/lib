package library.Managemnt.library.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import library.Managemnt.library.entity.User;



public interface UserRepository extends JpaRepository<User,Integer> {

}
