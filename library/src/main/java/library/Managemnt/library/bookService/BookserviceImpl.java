

package library.Managemnt.library.bookService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import library.Managemnt.library.Exceptions.BookNotFoundException;
import library.Managemnt.library.Repository.BookRepository;
import library.Managemnt.library.entity.Book;

@Service
public class BookserviceImpl  implements BookService{

	@Autowired
	private BookRepository bookRepository;
	
	
	public Book SaveBookById(Book book) {
	return	bookRepository.save(book);
	}
	
	public List<Book> GetAll(String sortBy){
		if("bookName".equalsIgnoreCase(sortBy)) {
			return bookRepository.findAll(Sort.by("bookName"));
		}else if("id".equalsIgnoreCase(sortBy)) {
			return bookRepository.findAll(Sort.by("bookId"));
		}else {
		return bookRepository.findAll();//default sorting
	}}
	
	public Book getBook(String bookName) {
		Book book=bookRepository.findByBookName(bookName);
	if(book==null) {
		throw new BookNotFoundException("no book found");
	}
	return book;
	}
	
	public Book updateBookQuantity(int bookId,int quantity) {
		Optional<Book> bookOptional=bookRepository.findById(bookId);
		if(!bookOptional.isPresent()) {
			throw new RuntimeException("record not found");
		}
		Book book=bookOptional.get();
		book.setQuantity(quantity);
		return bookRepository.save(book);
	}

	

	
	
}
