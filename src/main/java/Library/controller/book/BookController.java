package Library.controller.book;

import Library.controller.Controller;
import java.util.*;
import java.util.stream.Collectors;
import static java.util.Arrays.asList;
import Library.entity.Author;
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
        output.produce("============ BOOK MANAGEMENT ===============");
        printInstructions();
        receiveInputAndAct();
    }

    private void printInstructions() {
        output.produce("L - List of books");
        output.produce("F - Find book");
        output.produce("C - Create new book");
        output.produce("B - Return");
    }

    private void receiveInputAndAct() {
        String input = receiver.receiveLine().toUpperCase();
        switch (input) {
            case "L": {
                listAllBooks();
                break;
            }
            case "F": {
                findBook();
                break;
            }
            case "C": {
                createNewBook();
                break;
            }
            case "B": {
                return;
            }
            default: {
                output.produce("Action unrecognised.");
                printInstructions();
            }
        }
        receiveInputAndAct();
    }



    private void findBook() {
        output.produce("Enter to fragment to search by");
        List<Book> books = bookService.searchByKeyWord(receiver.receiveLine());
        output.produce("Found books:");
        books.forEach(book -> output.produce(book.toString()));
    }

    private void createNewBook() {
        output.produce("Authors (enter author numbers, separated by commas):");
        List<Author> authors = getAuthorsFromInput();
        output.produce("Title of the book:");
        String title = receiver.receiveLine();
        output.produce("Genre of the book: ");
        String genre = receiver.receiveLine();
        output.produce("Year of release of the book: ");
        Integer releaseDate = Integer.parseInt(receiver.receiveLine());
        bookService.saveNewBook(authors, title, genre, releaseDate);
        output.produce("New author saved successfully!");
    }

    private List<Author> getAuthorsFromInput() {
        List<Author> allAuthors = authorService.findAllAuthors();
        allAuthors.forEach(author -> output.produce(author.toString()));
        Map<UUID, Author> authorMap = allAuthors.stream().collect(Collectors.toMap(Author::getId, author -> author));
        List<String> input = asList(receiver.receiveLine().split(","));
        return input.stream()
                .map(value -> getAuthorFromMap(value, authorMap))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    private Optional<Author> getAuthorFromMap(String input, Map<UUID, Author> authorMap) {
        Author author = null;
        try {
            long numericValue = Long.parseLong(input);
            author = authorMap.get(numericValue);
            if (author == null) {
                output.produce("Author could not be found by given id: " + numericValue);
            }
        } catch (NumberFormatException e) {
            output.produce("Could not get author " + input + " as it's not a valid id");
        }
        return Optional.ofNullable(author);
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



/*    private void findBook() {
        output.produce("Enter to fragment to search by");
        List<Book> books = bookService.searchByNameFragment(receiver.receiveString());
        output.produce("Found books:");
        books.forEach(book -> output.produce(book.toString()));
    }*/


}
