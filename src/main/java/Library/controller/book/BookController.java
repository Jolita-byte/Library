package Library.controller.book;

import Library.controller.Controller;
import Library.entity.Book;
import Library.utilities.FileUtils;
import Library.utilities.JsonUtils;
import Library.service.AuthorService;
import Library.service.BookService;
import Library.utilities.input.InputReceiver;
import Library.utilities.output.OutputProducer;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.util.Arrays;
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

    public void runAllBooksFromFile(){
        allBooksFromFile();
    }

    public void runlistAllBooks() {
        listAllBooks();
    }

    public void runAllBooksToFile() {
        allBooksToFile();
    }

    public void runFindBook() {
        findBook();
    }

    private void listAllBooks() {
        output.produce("==== All books ====");
        bookService.findAllBooks().forEach(book -> output.produce(book.toString()));
        output.produce("=====================");
    }

    private void allBooksFromFile() {
        String filePath = "Books input.json";
        Book [] books = null;
        try {
            books = JsonUtils.fromJson(FileUtils.readFromFile(filePath), Book[].class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        bookService.saveAllBooks(Arrays.asList(books));
        output.produce("\u001B[45m" + "Books imported succesfully from file: " + filePath);
        output.produce("\033[0m");
    }


    private void allBooksToFile() {
        String filePath = "Books.json";
        if (!bookService.findAllBooks().isEmpty()) {
            try {
                FileUtils.writeToFile(filePath, JsonUtils.toPrettyJson(bookService.findAllBooks()));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            output.produce("\u001B[45m" + "File " + filePath + " was created");
            output.produce("\033[0m");
        } else output.produce("\u001B[45m" + "There is no books");
        output.produce("\033[0m");
    }



    private void findBook() {
        output.produce("Enter to fragment to search by");
        List<Book> books = bookService.searchByNameFragment(receiver.receiveString());
        output.produce("Found books:");
        books.forEach(book -> output.produce(book.toString()));
    }


}
