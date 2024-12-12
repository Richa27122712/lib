 package library.Managemnt.library.bookService;

import java.util.List;

import library.Managemnt.library.DTO.BookDTO;
import library.Managemnt.library.entity.BookIssue;

public interface BookIsssueService {
	BookIssue SaveBookIssue(int bookId,int userId,int loanPeriodDays);

	String getDueDays(int bookIssueId);
	 List<BookIssue> GetAll();

	List<BookDTO> getBooksByUserId(int userId);

	void UnassignedBook(int userId, int bookId);
}
