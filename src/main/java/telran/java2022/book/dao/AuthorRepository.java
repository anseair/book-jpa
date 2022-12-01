package telran.java2022.book.dao;

import java.util.stream.Stream;

import org.springframework.data.repository.CrudRepository;

import telran.java2022.book.model.Author;
import telran.java2022.book.model.Book;

public interface AuthorRepository extends CrudRepository<Author, String> {
}
