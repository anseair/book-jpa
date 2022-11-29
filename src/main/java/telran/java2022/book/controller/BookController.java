package telran.java2022.book.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import telran.java2022.book.dto.AuthorDto;
import telran.java2022.book.dto.BookDto;
import telran.java2022.book.service.BookService;

@RestController
@RequiredArgsConstructor
public class BookController {

	final BookService bookService;
	
	@PostMapping("/book")
	public boolean addBook(@RequestBody BookDto bookDto) {
		return bookService.addBook(bookDto);
	}

	@GetMapping("/book/{isbn}")
	public BookDto findBookByIsbn(@PathVariable String isbn) {
		return bookService.findBookByIsbn(isbn);
	}

	@DeleteMapping("/book/{isbn}")
	public BookDto removeBookByIsbn(@PathVariable String isbn) {
		return bookService.removeBookByIsbn(isbn);
	}

	@PutMapping("/book/{isbn}/title/{title}")
	public BookDto updateBook(@PathVariable String isbn, @PathVariable String title) {
		return bookService.updateBook(isbn, title);
	}

	@GetMapping("/books/author/{author}")
	public Iterable<BookDto> findBooskByAuthor(@PathVariable String authorName) {
		return bookService.findBooskByAuthor(authorName);
	}

	@GetMapping("/books/publisher/{publisher}")
	public Iterable<BookDto> findBooskByPublisher(@PathVariable String publisherName) {
		return bookService.findBooskByPublisher(publisherName);
	}

	@GetMapping("/authors/book/{isbn}")
	public Iterable<AuthorDto> findBookByAuthors(@PathVariable String isbn) {
		return bookService.findBookByAuthors(isbn);
	}

	@GetMapping("/publishers/author/{author}")
	public Iterable<String> findPublishersByAuthor(@PathVariable String publisherName) {
		return bookService.findPublishersByAuthor(publisherName);
	}

	@DeleteMapping("author/{author}")
	public AuthorDto removeAuthor(@PathVariable String authorName) {
		return bookService.removeAuthor(authorName);
	}
	
	 

}
