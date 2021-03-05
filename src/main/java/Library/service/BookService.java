package Library.service;

import Library.entity.Book;
import Library.repository.Book.BookRepository;

import java.util.List;

public class BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;

    public BookService(BookRepository bookRepository,
                       AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
    }

    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    public List<Book> searchByNameFragment(String fragment) {
        return bookRepository.searchByNameFragment(fragment);
    }

    public void saveAllBooks(List<Book> books){
        books.forEach(b -> bookRepository.save(b));
    }
}
