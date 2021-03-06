package Library.controller.reader;

import Library.controller.Controller;
import Library.entity.Reader;
import Library.service.ReaderService;
import Library.utilities.input.InputReceiver;
import Library.utilities.output.OutputProducer;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ReaderController implements Controller {

    private final ReaderService readerService;
    private final InputReceiver receiver;
    private final OutputProducer output;

    public ReaderController(ReaderService readerService, InputReceiver receiver, OutputProducer output) {
        this.readerService = readerService;
        this.receiver = receiver;
        this.output = output;
    }

    @Override
    public void run() {
        output.produce("===== Reader MANAGEMENT =====");
        printInstructions();
        receiveInputAndAct();
    }

    private void printInstructions() {
        output.produce("L - List of readers");
        output.produce("F - Find reader");
        output.produce("C - Create new reader");
        output.produce("B - Return");
    }

    private void receiveInputAndAct() {
        String input = receiver.receiveLine().toUpperCase();
        switch (input) {
            case "L": {
                listAllReaders();
                break;
            }
            case "F": {
                findReader();
                break;
            }
            case "C": {
                createNewReader();
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

    private void createNewReader() {
        output.produce("First name of the new reader:");
        String name = receiver.receiveLine();
        name = checkStringIsEmptyNoSpaceNotNull(name, " name of the new reader");
        output.produce("Surname of the new reader:");
        String surname = receiver.receiveLine();
        surname = checkStringIsEmptyNoSpaceNotNull(surname, " surname of the new reader");
        output.produce("Email of the new reader:");
        String email = receiver.receiveLine();
        email = checkStringIsEmptyNoSpaceNotNull(email, " email of the new reader");
        output.produce("Phone number of the new reader:");
        String phoneNumber = receiver.receiveLine();
        phoneNumber = checkStringIsEmptyNoSpaceNotNull(phoneNumber, " phoneNumber of the new reader");
        output.produce("Active since: Enter a date (like month/day/year): ");
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("M/d/yyyy");
        LocalDate activeSince = LocalDate.parse(receiver.receiveLine(), dateFormat);
        readerService.saveNewReader(name, surname, email, phoneNumber, activeSince);
        output.produce("New Reader saved successfully!");
    }

    private String checkStringIsEmptyNoSpaceNotNull(String name, String s) {
        while (name == null || name.isEmpty() || name.trim().equals("")) {
            System.out.println("Please enter" + s);
            name = receiver.receiveLine();
        }
        return name;
    }

    private void findReader() {
        output.produce("Enter a key word to search by");
        List<Reader> readers = readerService.findReaderByKeyword(receiver.receiveLine());
        output.produce("Found authors:");
        readers.forEach(author -> output.produce(author.toString()));
    }
    private void listAllReaders() {
        output.produce("============== All readers ===============");
        readerService.findAllReaders().forEach(reader -> output.produce(reader.toString()));
        output.produce("=============================================");
    }




}
