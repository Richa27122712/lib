package library.Managemnt.library.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import library.Managemnt.library.entity.Book;

public interface BookRepository extends JpaRepository<Book,Integer>{

	Book findByBookName(String bookName);

}
