package Library.service;


import Library.entity.Reader;
import Library.repository.reader.ReaderRepository;

import java.time.LocalDate;
import java.util.List;

public class ReaderService {
    private final ReaderRepository readerRepository;


    public ReaderService(ReaderRepository readerRepository) {
        this.readerRepository = readerRepository;
    }

    public Reader saveNewReader(String name, String surname, String email, String phoneNumber, LocalDate activeSince) {
        Reader reader = new Reader(name, surname, email, phoneNumber, activeSince);
        readerRepository.save(reader);
        return reader;
    }

    public List<Reader> findAllReaders() {
        return readerRepository.findAll();
    }

    public Reader updateReader(Reader reader) {
        readerRepository.save(reader);
        return reader;
    }

    public List<Reader> findReaderByKeyword(String keyWord) {
        return readerRepository.searchByKeyWord(keyWord);
    }
}
