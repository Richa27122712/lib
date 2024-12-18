package library.Managemnt.library.bookService;

import java.util.List;

import org.springframework.stereotype.Service;

import library.Managemnt.library.entity.Book;
@Service
public interface BookService {
 
	
	Book SaveBookById(Book book);
List<Book> GetAll(String sortBy);

Book getBook(String bookName);
Book updateBookQuantity(int bookId,int quantity);
}
