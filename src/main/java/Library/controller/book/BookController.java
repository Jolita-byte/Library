package Library.controller.book;

import Library.controller.Controller;
import Library.entity.Book;
import Library.service.AuthorService;
import Library.service.BookService;
import Library.utilities.input.InputReceiver;
import Library.utilities.output.OutputProducer;

import java.util.List;

public class BookController implements Controller {

    private final BookService bookService;
    private final AuthorService authorService;
    private final InputReceiver receiver;
    private final OutputProducer output;


    public BookController(BookService bookService, AuthorService authorService, InputReceiver receiver, OutputProducer output) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.receiver = receiver;
        this.output = output;
    }
    @Override
    public void run() {

    }
    public void runlistAllBooks(){
            listAllBooks();

    }

    public void runFindBook() {
        findBook();
    }

    private void listAllBooks() {
        output.produce("==== All authors ====");
        bookService.findAllBooks().forEach(book -> output.produce(book.toString()));
        output.produce("=====================");
    }
    private void findBook() {
        output.produce("Enter to fragment to search by");
        List<Book> books = bookService.searchByNameFragment(receiver.receiveString());
        output.produce("Found books:");
        books.forEach(book -> output.produce(book.toString()));
    }


}
