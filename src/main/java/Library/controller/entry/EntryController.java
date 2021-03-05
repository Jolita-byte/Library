package Library.controller.entry;

import Library.controller.Controller;
import Library.controller.author.AuthorController;
import Library.controller.book.BookController;
import Library.service.BookService;
import Library.utilities.input.InputReceiver;
import Library.utilities.output.OutputProducer;

public class EntryController implements Controller {
    private final AuthorController authorController;
    private final BookController bookController;
    private final Controller readerController;
    private final InputReceiver receiver;
    private final OutputProducer output;

    public EntryController(AuthorController authorController,
                           BookController bookController,
                           Controller readerController,
                           InputReceiver receiver,
                           OutputProducer output) {
        this.authorController = authorController;
        this.bookController = bookController;
        this.readerController = readerController;
        this.receiver = receiver;
        this.output = output;}

/*public class EntryController {

    private final AuthorController authorController;
    private final BookController bookController;
    private final InputReceiver receiver;
    private final OutputProducer output;

    public EntryController(AuthorController authorController, BookController bookController, InputReceiver receiver, OutputProducer output) {
        this.authorController = authorController;
        this.bookController = bookController;
        this.receiver = receiver;
        this.output = output;
    }*/

    @Override
    /*public void run() {
        printMenu();
        receiveInputAndAct();
    }*/

    public void run() {
        output.produce("===== Welcome to Library! =====");
        printInstructions();
        receiveInputAndAct2();
    }

    public static void printMenu() {
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("Welcome to the library!");
        System.out.println("Search for a desired book of yours:");
        System.out.println(

                "1. Import the list of all books from file. \n" +
                "2. See the list of all books. \n" +
                "3. Write the list of all books to file. \n" +
                "4. See the list of all authors. \n" +
                "5. Find the book according criterias. \n" +
                "6. Quit.");
    }
    private void printInstructions() {
        output.produce("A - Author management");
        output.produce("B - Book management");
        output.produce("R - Reader management");
        output.produce("I - Import from file");
        output.produce("E - Export to file");
        output.produce("X - Exit");
    }

    private void receiveInputAndAct2() {
        String input = receiver.receiveLine().toUpperCase();
        switch (input) {
            case "A": {
                authorController.run();
                break;
            }
            case "B": {
                bookController.run();
                break;
            }
            case "R": {
                readerController.run();
                break;
            }
            case "I":
                bookController.runAllBooksFromFile();
                break;
            case "E":
                bookController.runAllBooksToFile();
                break;
            case "X": {
                return;
            }
            default: {
                output.produce("Action unrecognised.");
                printInstructions();
            }
        }
        receiveInputAndAct();
    }




    private void receiveInputAndAct() {
        Integer input = receiver.receiveInt();
        switch (input) {
            case 1:
                bookController.runAllBooksFromFile();
                break;
            case 2:
                bookController.runlistAllBooks();
                break;
            case 3:
                bookController.runAllBooksToFile();
                break;
            case 4:
                authorController.runlistAllAuthors();
                break;
            case 5:
                bookController.runFindBook();
                break;
            case 6:
                return;
        }
        printMenu();
        receiveInputAndAct();
    }
}

