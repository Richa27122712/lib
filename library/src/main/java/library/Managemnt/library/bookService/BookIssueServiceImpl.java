package library.Managemnt.library.bookService;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import library.Managemnt.library.DTO.BookDTO;
import library.Managemnt.library.Exceptions.MaxBookIssuedException;
import library.Managemnt.library.Exceptions.UserAlreadyHasBookException;
import library.Managemnt.library.Repository.BookIssueRepository;
import library.Managemnt.library.Repository.BookRepository;
import library.Managemnt.library.Repository.UserRepository;
import library.Managemnt.library.entity.Book;
import library.Managemnt.library.entity.BookIssue;
import library.Managemnt.library.entity.User;

@Service
public class BookIssueServiceImpl implements BookIsssueService {

	@Autowired
	private BookIssueRepository bookIssueRepository;

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private UserRepository userRepository;

	public String getDueDays(int bookIssueId) {

		Optional<BookIssue> bookIssueOptional = bookIssueRepository.findById(bookIssueId);
		if (!bookIssueOptional.isPresent()) {
			throw new RuntimeException("Record not find");
		}
		BookIssue bookIssue = bookIssueOptional.get();
		LocalDate today = LocalDate.now();
		LocalDate returnDate = bookIssue.getReturnDate();
		long days = ChronoUnit.DAYS.between(today, returnDate);
		long fine = days * 20;
		if (days < 0) {
			return "due days" + days;
		}
		return "over due days" + Math.abs(days) + "Fine :" + fine;

	}

	public BookIssue SaveBookIssue(int bookId, int userId, int loanPeriodDays) {

		BookIssue bookissue = bookIssueRepository.findByUser_userIdAndBook_bookId(userId, bookId);
		if (bookissue != null) {
			throw new UserAlreadyHasBookException("user is already assisgened with same book");
		}

		Optional<Book> bookOptional = bookRepository.findById(bookId);
		if (!bookOptional.isPresent()) {
			throw new RuntimeException("book not found");
		}
		Optional<User> userOptional = userRepository.findById(userId);
		if (!userOptional.isPresent()) {
			throw new RuntimeException("user not found");

		}

		long BookIssueToUser = bookIssueRepository. countByUser_UserId(userId);
		if (BookIssueToUser >= 15) {
			throw new MaxBookIssuedException("User has reached the limit of books");
		}

		Book book = bookOptional.get();
		User user = userOptional.get();

  		if (book.getAvailableQuantity() == 0) {
			throw new RuntimeException("All books are assigned");
		}

		book.setAvailableQuantity(book.getAvailableQuantity() - 1);
		bookRepository.save(book);

		BookIssue bookIssue = new BookIssue();
		bookIssue.setBook(book);
		bookIssue.setUser(user);

		LocalDate issueDate = LocalDate.now();
		bookIssue.setIssueDate(issueDate);

		LocalDate returnDate = issueDate.plusDays(loanPeriodDays);
		bookIssue.setReturnDate(returnDate);

		bookIssue.setStatus("issued");

		return bookIssueRepository.save(bookIssue);
	}

	@Override
	public List<BookIssue> GetAll() {
		List<BookIssue> bookIssue = bookIssueRepository.findAll();

		for (int i = 0; i < bookIssue.size(); i++) {
			BookIssue book1 = bookIssue.get(i);
			book1.setDueDays(getDueDays(book1.getBookIssueId()));

//			Book book = book1.getBook();
//
//			long issueCount = bookIssueRepository.countByBook_bookIdAndStatus(book.getBookId(), "issued");
//			int availableQuantity = book.getQuantity() - (int) issueCount;
//
//			book.setAvailableQuantity(availableQuantity);
		}
		return bookIssue;
	}

	public List<BookDTO> getBooksByUserId(int userId) {
		return bookIssueRepository.findBooksByUserId(userId);

	}
	
	
	
	

	@Override
	@Transactional
	public void UnassignedBook(int userId, int bookId) {
		BookIssue bookissue = bookIssueRepository.findByUser_userIdAndBook_bookId(userId, bookId);
		if (bookissue == null) {
			throw new RuntimeException("user has no book assigened with this bookid");
		}
		bookIssueRepository.delete(bookissue);
		Book book = bookissue.getBook();
		
		int availableQuantity = book.getAvailableQuantity()+1;

		book.setAvailableQuantity(availableQuantity);

		bookRepository.save(book);
		System.out.println(
				"Book is successsfully unassingned" + "book availableQuantity :" + book.getAvailableQuantity());

	}

}
