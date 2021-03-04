package Library.controller.author;

import Library.controller.Controller;
import Library.service.AuthorService;
import Library.utilities.input.InputReceiver;
import Library.utilities.output.OutputProducer;

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

    }

    public void runlistAllAuthors() {
        listAllAuthors();
    }
    private void listAllAuthors() {
        output.produce("==== All authors ====");
        authorService.findAllAuthors().forEach(author -> output.produce(author.toString()));
        output.produce("=====================");
    }

}
