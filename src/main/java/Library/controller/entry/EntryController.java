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


    public void run() {
        output.produce("===== Welcome to Library! =====");
        printInstructions();
        receiveInputAndAct();
    }


    private void printInstructions() {
        output.produce("A - Author management");
        output.produce("B - Book management");
        output.produce("R - Reader management");
        output.produce("I - Import from file");
        output.produce("E - Export to file");
        output.produce("X - Exit");
    }

    private void receiveInputAndAct() {
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


}

