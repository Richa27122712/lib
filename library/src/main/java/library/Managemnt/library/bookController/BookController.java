package library.Managemnt.library.bookController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import library.Managemnt.library.bookService.BookService;
import library.Managemnt.library.entity.Book;

@RestController
@RequestMapping("/library/books")
public class BookController {

    @Autowired
    private BookService bookService;
    
    @PostMapping("/add")
    public ResponseEntity<Book> SaveBook(@RequestBody Book book) {
        Book savedbook = bookService.SaveBookById(book);
        return new ResponseEntity<>(savedbook, HttpStatus.CREATED);
    }
    @GetMapping("/get")
    public ResponseEntity<List<Book>> getAllBooks(){
  List<Book> books=  	bookService.GetAll();
    	return new ResponseEntity<>(books,HttpStatus.OK);
    }
    
    @GetMapping("/getBook/{bookName}")
    public Book getBookbySearch(@PathVariable String bookName) {
    	return bookService.getBook(bookName);
    }
    @PatchMapping("/updateQuantity/{bookId}")
    public Book updateQuantityBook(@PathVariable int bookId, @RequestParam int quantity) {
    return bookService.updateBookQuantity(bookId,quantity);
}
}