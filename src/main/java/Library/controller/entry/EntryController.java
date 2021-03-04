package Library.controller.entry;


import Library.controller.Controller;
import Library.controller.author.AuthorController;
import Library.controller.book.BookController;
import Library.service.BookService;
import Library.utilities.input.InputReceiver;
import Library.utilities.output.OutputProducer;

public class EntryController {

    private final AuthorController authorController;
    private final BookController bookController;
    private final InputReceiver receiver;
    private final OutputProducer output;

    public EntryController(AuthorController authorController, BookController bookController, InputReceiver receiver, OutputProducer output) {
        this.authorController = authorController;
        this.bookController = bookController;
        this.receiver = receiver;
        this.output = output;
    }

    public void run() {
        printMenu();
        receiveInputAndAct();
    }

    public static void printMenu() {
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("Welcome to the library!");
        System.out.println("Search for a desired book of yours:");
        System.out.println("Find: \n" +
                "1. See the list of all books. \n" +
                "2. See the list of all authors. \n" +
                "3. Find the book according criterias. \n" +
                "4. Quit.");
    }

    private void receiveInputAndAct() {
        Integer input = receiver.receiveInt();
        boolean quit = false;
        boolean forward = true;

        while (!quit) {
            switch (input) {
                case 1:
                    bookController.runlistAllBooks();
                    break;
                case 2:
                    authorController.runlistAllAuthors();
                    break;
                case 3:
                    bookController.runFindBook();
                    break;
                case 4:
                    quit = true;
                    break;
            }
        }
        receiveInputAndAct();
    }
}