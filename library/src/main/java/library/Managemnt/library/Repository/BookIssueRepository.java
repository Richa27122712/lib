package library.Managemnt.library.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import library.Managemnt.library.DTO.BookDTO;
import library.Managemnt.library.entity.BookIssue;

public interface BookIssueRepository extends JpaRepository<BookIssue,Integer> {

	 long countByUser_UserId(int userId);
	 
	 
	 
	 @Query("SELECT new library.Managemnt.library.DTO.BookDTO(b.bookId,b.bookName)" + 
	 "From Book b JOIN  BookIssue bi on b.bookId=bi.book.bookId where bi.user.userId=:userId" )
	    List<BookDTO> findBooksByUserId(@Param("userId") int userId);



	BookIssue findByUser_userIdAndBook_bookId(int userId, int bookId);



	long countByBook_bookIdAndStatus(int bookId, String Status);
	
	
	
	}
	
	
