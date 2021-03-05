package Library.service;

import Library.entity.Author;
import Library.entity.Book;
import Library.repository.Book.BookRepository;

import java.util.List;
import java.util.Set;

public class BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;

    public BookService(BookRepository bookRepository, AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
    }

    public Book saveNewBook(Set<Author> authors, String title, String genre, Integer releaseDate) {
        Book book = new Book(authors, title, genre, releaseDate);
        bookRepository.save(book);
        addBookToAuthors(authors, book);
        return book;
    }

    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    public List<Book> searchByKeyWord(String keyWord) {
        return bookRepository.searchByKeyWord(keyWord);
    }


    private void addBookToAuthors(Set<Author> authors, Book book) {
        authors.forEach(author -> {
            author.getBooks().add(book);
            authorService.updateAuthor(author);
        });
    }

}