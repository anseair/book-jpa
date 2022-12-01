package telran.java2022.book.dao;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import telran.java2022.book.dto.BookDto;
import telran.java2022.book.model.Author;
import telran.java2022.book.model.Book;

public interface BookRepository extends CrudRepository<Book, String> {
	
	
	@Query("select p from Book p where p.publisher.publisherName=:publisherName")
	Stream<Book> findByPublisherPublisherName(@Param("publisherName") String publisherName);
	
	List<Book> findByAuthorsNameIgnoreCase(String authorName);
//	Stream<Book> findByPublisherPublisherName(String publisherName);
}
