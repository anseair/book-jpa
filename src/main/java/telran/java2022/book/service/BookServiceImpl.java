package telran.java2022.book.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import telran.java2022.book.dao.AuthorRepository;
import telran.java2022.book.dao.BookRepository;
import telran.java2022.book.dao.PublisherRepository;
import telran.java2022.book.dto.AuthorDto;
import telran.java2022.book.dto.BookDto;
import telran.java2022.book.dto.exceptions.EntityNotFoundEsception;
import telran.java2022.book.model.Author;
import telran.java2022.book.model.Book;
import telran.java2022.book.model.Publisher;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

	final BookRepository bookRepository;
	final AuthorRepository authorRepository;
	final PublisherRepository publisherRepository;
	final ModelMapper modelMapper;
	
	@Override
	@Transactional
	public boolean addBook(BookDto bookDto) {
		if (bookRepository.existsById(bookDto.getIsbn())) {
			return false;
		}
		Publisher publisher = publisherRepository.findById(bookDto.getPublisher())
				.orElse(publisherRepository.save(new Publisher(bookDto.getPublisher())));
		Set<Author> authors = bookDto.getAuthors().stream()
				.map(a -> authorRepository.findById(a.getName())
						.orElse(authorRepository.save(new Author(a.getName(), a.getBirthDate()))))
				.collect(Collectors.toSet());
		Book book = new Book(bookDto.getIsbn(), bookDto.getTitle(), authors , publisher);
		bookRepository.save(book);
		return true;
	}

	@Override
	public BookDto findBookByIsbn(String isbn) {
		Book book = bookRepository.findById(isbn).orElseThrow(EntityNotFoundEsception::new);
		Set<AuthorDto> authors = book.getAuthors().stream()
				.map(a -> new AuthorDto(a.getName(), a.getBirthDate()))
				.collect(Collectors.toSet());
		String publisher = book.getPublisher().getPublisherName();
		BookDto bookDto = new BookDto(book.getIsbn(), book.getTitle(), authors, publisher);
		return bookDto;

	}

	@Override
	public BookDto removeBookByIsbn(String isbn) {
		Book book = bookRepository.findById(isbn).orElseThrow(EntityNotFoundEsception::new);
		Set<AuthorDto> authors = book.getAuthors().stream()
				.map(a -> new AuthorDto(a.getName(), a.getBirthDate()))
				.collect(Collectors.toSet());
		String publisher = book.getPublisher().getPublisherName();
		BookDto bookDto = new BookDto(book.getIsbn(), book.getTitle(), authors, publisher);
		bookRepository.delete(book);
		return bookDto;
	}

	@Override
	@Transactional
	public BookDto updateBook(String isbn, String title) {
		Book book = bookRepository.findById(isbn).orElseThrow(EntityNotFoundEsception::new);
		book.setTitle(title);
		Set<AuthorDto> authors = book.getAuthors().stream()
				.map(a -> new AuthorDto(a.getName(), a.getBirthDate()))
				.collect(Collectors.toSet());
		String publisher = book.getPublisher().getPublisherName();
		BookDto bookDto = new BookDto(book.getIsbn(), book.getTitle(), authors, publisher);
		return bookDto;
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<BookDto> findBooskByAuthor(String authorName) {
		//FIXME
		
		Author author = authorRepository.findById(authorName).orElseThrow(EntityNotFoundEsception::new);
		List<Book> books = bookRepository.findByAuthorsNameIgnoreCase(authorName);
		Set<AuthorDto> authors = books.get(0).getAuthors().stream()
				.map(a -> new AuthorDto(a.getName(), a.getBirthDate()))
				.collect(Collectors.toSet());	
		return books.stream()
				.map(b -> new BookDto(b.getIsbn(), b.getTitle(),authors, b.getPublisher().getPublisherName()))
				.collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<BookDto> findBooskByPublisher(String publisherName) {
		//FIXME
		
		Publisher publisher = publisherRepository.findById(publisherName).orElseThrow(EntityNotFoundEsception::new);
		List<Book> books = bookRepository.findByPublisherPublisherName(publisherName).collect(Collectors.toList());
		Set<AuthorDto> authors = books.get(0).getAuthors().stream()
				.map(a -> new AuthorDto(a.getName(), a.getBirthDate()))
				.collect(Collectors.toSet());
		
		return books.stream()
				.map(b -> new BookDto(b.getIsbn(), b.getTitle(),authors, b.getPublisher().getPublisherName()))
				.collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<AuthorDto> findBookByAuthors(String isbn) {
		Book book = bookRepository.findById(isbn).orElseThrow(EntityNotFoundEsception::new);
		return book.getAuthors().stream()
				.map(a -> new AuthorDto(a.getName(), a.getBirthDate()))
				.collect(Collectors.toSet());
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<String> findPublishersByAuthor(String authorName) {
		List<String> publishers = bookRepository.findByAuthorsNameIgnoreCase(authorName).stream()
									.map(b -> b.getPublisher().getPublisherName())
									.collect(Collectors.toList());
		return publishers;
	}

	@Override
	public AuthorDto removeAuthor(String authorName) {
		//FIXME
		Author author = authorRepository.findById(authorName).orElseThrow(EntityNotFoundEsception::new);
		List<Book> books = bookRepository.findByAuthorsNameIgnoreCase(authorName);
		
		books.forEach(b -> b.getAuthors().remove(author));
		authorRepository.delete(author);
		AuthorDto authorDto = new AuthorDto(author.getName(), author.getBirthDate()); 
		return authorDto;
	}

}
