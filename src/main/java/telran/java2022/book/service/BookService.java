package telran.java2022.book.service;

import telran.java2022.book.dto.AuthorDto;
import telran.java2022.book.dto.BookDto;

public interface BookService {
	boolean addBook(BookDto bookDto);

	BookDto findBookByIsbn(String isbn);

	BookDto removeBookByIsbn(String isbn);

	BookDto updateBook(String isbn, String title);

	Iterable<BookDto> findBooskByAuthor(String authorName);

	Iterable<BookDto> findBooskByPublisher(String publisherName);

	Iterable<AuthorDto> findBookByAuthors(String isbn);

	Iterable<String> findPublishersByAuthor(String publisherName);

	AuthorDto removeAuthor(String authorName);

}
