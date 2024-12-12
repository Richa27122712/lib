package library.Managemnt.library.bookController;

import java.util.List;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import library.Managemnt.library.DTO.BookDTO;
import library.Managemnt.library.bookService.BookIsssueService;
import library.Managemnt.library.entity.BookIssue;

@RestController
@RequestMapping("/userAssign")

public class UserAssignController {

	@Autowired
private	BookIsssueService bookIssueService;
	
	@PostMapping("/ ")
	
	public ResponseEntity<BookIssue> SaveAllDetail(@RequestParam int bookId,@RequestParam int userId,@RequestParam int loanPeriodDays) {
		BookIssue saveAssignedBook=bookIssueService.SaveBookIssue(bookId,userId,loanPeriodDays);
		return new ResponseEntity<>(saveAssignedBook,HttpStatus.CREATED) ;
		
	}
	@GetMapping("/getAssignedbooks")
	public ResponseEntity<List<BookIssue>> GetAllAssignedBooks(){
	List<BookIssue> assigedBook=	bookIssueService.GetAll();
		return new ResponseEntity<>(assigedBook,HttpStatus.OK);
	}
	@GetMapping("/quantity/{bookIssueId}")
	public ResponseEntity<String> getDueStatus(@PathVariable int bookIssueId){
	String message=bookIssueService.getDueDays(bookIssueId);
		return new ResponseEntity<>(message,HttpStatus.OK);
		
	}
	@GetMapping("/books/{userId}")
	public ResponseEntity<List<BookDTO>> getbooksDetailByUserId(@PathVariable int userId){
		List<BookDTO> books= bookIssueService.getBooksByUserId(userId);
		return new ResponseEntity<>(books,HttpStatus.OK);
	}
	@DeleteMapping("/unassignedBook/{userId}/{bookId}")
	public ResponseEntity<String> deleteUnassignedBook(@PathVariable int userId, @PathVariable int bookId){
		bookIssueService.UnassignedBook(userId,bookId);
		return new ResponseEntity<>("Book is unassigned",HttpStatus.OK);
	}
	
}
