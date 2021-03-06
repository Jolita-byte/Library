package Library.service;

import Library.entity.Author;
import Library.entity.Book;
import Library.repository.book.BookRepository;
import java.util.List;

public class BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;


    public BookService(BookRepository bookRepository, AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
    }

    public Book saveNewBook(List<Author> authors, String title, String genre, Integer releaseDate) {
        Book book = new Book(authors, title, genre, releaseDate);
        bookRepository.save(book);
        return book;
    }

    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    public List<Book> searchByKeyWord(String keyWord) {
        return bookRepository.searchByKeyWord(keyWord);
    }

    public void saveAllBooks(List<Book> books){
        books.forEach(b -> bookRepository.save(b));
    }

}
