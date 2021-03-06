package Library.controller.author;

import Library.controller.Controller;
import Library.entity.Author;
import Library.service.AuthorService;
import Library.utilities.input.InputReceiver;
import Library.utilities.output.OutputProducer;

import java.util.List;

public class AuthorController implements Controller {
    private final AuthorService authorService;
    private final InputReceiver receiver;
    private final OutputProducer output;

    public AuthorController(AuthorService authorService, InputReceiver receiver, OutputProducer output) {
        this.authorService = authorService;
        this.receiver = receiver;
        this.output = output;
    }

    @Override
    public void run() {
        output.produce("*****************Author management*****************");
        printInstructions();
        receiveInputAndAct();
    }

    private void printInstructions() {
        output.produce("L - List of authors");
        output.produce("F - Find author");
        output.produce("C - Create new author");
        output.produce("B - Back");
    }

    private void receiveInputAndAct() {
        String input = receiver.receiveLine().toUpperCase();
        switch (input) {
            case "L": {
                listAllAuthors();
                break;
            }
            case "F": {
                findAuthor();
                break;
            }
            case "C": {
                createNewAuthor();
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

    private void createNewAuthor() {
        output.produce("First name of the new author:");
        String name = receiver.receiveLine();
        name = checkStringIsEmptyNoSpaceNotNull(name, " name of the new author");
        output.produce("Surname of the new author:");
        String surname = receiver.receiveLine();
        surname = checkStringIsEmptyNoSpaceNotNull(surname, " surname of the new author");
        authorService.saveNewAuthor(name, surname);
        output.produce("New author saved successfully!");
    }

    private String checkStringIsEmptyNoSpaceNotNull(String name, String s) {
        while (name == null || name.isEmpty() || name.trim().equals("")) {
            System.out.println("Please enter" + s);
            name = receiver.receiveLine();
        }
        return name;
    }

    private void findAuthor() {
        output.produce("Enter to fragment to search by");
        List<Author> authors = authorService.findAuthorsByKeyWord(receiver.receiveLine());
        output.produce("Found authors:");
        authors.forEach(author -> output.produce(author.toString()));
    }

    private void listAllAuthors() {
        output.produce("============= All authors ==============");
        authorService.findAllAuthors().forEach(author -> output.produce(author.toString()));
        output.produce("=======================================");
    }


   // }

    public void runlistAllAuthors() {
        listAllAuthors();
    }


}
