package Library.controller.entry;

import Library.controller.Controller;
import Library.util.input.InputReceiver;
import Library.util.output.OutputProducer;

public class EntryController implements Controller {
    private final Controller authorController;
    private final Controller bookController;
    private final Controller readerController;
    private final InputReceiver receiver;
    private final OutputProducer output;

    public EntryController(Controller authorController,
                           Controller bookController,
                           Controller readerController,
                           InputReceiver receiver,
                           OutputProducer output) {
        this.authorController = authorController;
        this.bookController = bookController;
        this.readerController = readerController;
        this.receiver = receiver;
        this.output = output;
    }

    @Override
    public void run() {
        output.produce("===== Welcome to Library! =====");
        printInstructions();
        receiveInputAndAct();
    }
    private void printInstructions() {
        output.produce("A - Author management");
        output.produce("B - Book management");
        output.produce("R - Reader management");
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
