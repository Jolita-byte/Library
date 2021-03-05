package Library;


import Library.controller.author.AuthorController;
import Library.controller.book.BookController;
import Library.controller.entry.EntryController;
import Library.controller.reader.ReaderController;
import Library.entity.*;
import Library.repository.Author.AuthorRepository;
import Library.repository.Book.BookRepository;
import Library.repository.BookBorrowing.BookBorrowingRepository;
import Library.repository.Reader.ReaderRepository;
import Library.repository.Reservation.ReservationRepository;
import Library.service.AuthorService;
import Library.service.BookService;
import Library.service.ReaderService;
import Library.util.input.DefaultInputReceiver;
import Library.util.input.InputReceiver;
import Library.util.output.DefaultOutputProducer;
import Library.util.output.OutputProducer;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;

public class Project {

    public static final String HIBERNATE_CONFIGURATION = "hibernate.cfg.xml";
    private EntityManager entityManager;
    private BookRepository bookRepository;
    private AuthorRepository authorRepository;

    public Project() {
        entityManager = entityManager();
        bookRepository = new BookRepository(entityManager);
        authorRepository = new AuthorRepository(entityManager);
        //run1();
        constructEntryController(entityManager).run();
    }

    private EntryController constructEntryController(EntityManager entityManager) {
        InputReceiver receiver = new DefaultInputReceiver();
        OutputProducer output = new DefaultOutputProducer();
        AuthorService authorService = new AuthorService(new AuthorRepository(entityManager));
        BookService bookService = new BookService(new BookRepository(entityManager), authorService);
        ReaderService readerService = new ReaderService(new ReaderRepository(entityManager));

        AuthorController authorController = new AuthorController(authorService, receiver, output);
        BookController bookController = new BookController(bookService, authorService, receiver, output);
        ReaderController readerController = new ReaderController(readerService, receiver, output);
        return new EntryController(authorController, bookController, readerController, receiver, output);
    }

    private EntityManager entityManager() {

        SessionFactory sessionFactory = new Configuration()
                .configure(HIBERNATE_CONFIGURATION)
                .addAnnotatedClass(Author.class)
                .addAnnotatedClass(Book.class)
                .addAnnotatedClass(Reader.class)
                .addAnnotatedClass(Reservation.class)
                .addAnnotatedClass(BookBorrowing.class)
                .buildSessionFactory();
        return sessionFactory.createEntityManager();

    }

    public void run1() {
        Author author1 = new Author("Levas", "Tolstojus");
        Book book1 = new Book(author1, "Ana Karenina", "Romanas", 1877);
        bookRepository.save(book1);

        Author author2 = new Author("Džeromas Deividass", "Selindžeris");
        Book book2 = new Book(author2, "Rugiuose prie bedugnės", "Romanas", 1951);
        bookRepository.save(book2);

        Author author3 = new Author("Džeromas Deividas", "Selindžeris");
        Book book3 = new Book(author3, "Rugiuose prie bedugnės", "Romanas", 1951);
        bookRepository.save(book3);

        Author author4 = new Author("Katherine", " Paterson");
        Book book4 = new Book(author4, "Tiltas į terabitiją", "Romanas", 2018);
        bookRepository.save(book4);

        Author authorMan = new Author("Tomas", "Manas");
        authorRepository.save(authorMan);

        Book bookKazkoks = new Book(authorMan, "Kazkoks", "mistika", 1912);
        bookRepository.save(bookKazkoks);

        Author authorLindgren = new Author("Astrid", "Lindgren");
        authorRepository.save(authorLindgren);

        Book bookKalis = new Book(authorLindgren, "Kalis Bliunkvistas", "vaikams", 1950);
        bookRepository.save(bookKalis);
        Book bookKalis2 = new Book(authorLindgren, "Kalis Bliunkvistas ir Rasmusas", "vaikams", 1960);
        bookRepository.save(bookKalis2);
        Book bookPepe = new Book(authorLindgren, "Pepe", "vaikams", 1965);
        bookRepository.save(bookPepe);
        Book bookRonja = new Book(authorLindgren, "Ronja plesiko dukte", "vaikams", 1970);
        bookRepository.save(bookRonja);
        Author authorVernas = new Author("Ziulis", "Vernas");
        authorRepository.save(authorVernas);
        Book bookMenulis = new Book(authorVernas, "Kelione i Menuli", "nuotykiu", 1870);
        bookRepository.save(bookMenulis);
        Book book1000 = new Book(authorVernas, "100 myliu po vandeniu", "nuotykiu", 1880);
        bookRepository.save(book1000);
        Book bookGranto = new Book(authorVernas, "Kapitono Granto vaikai", "nuotykiu", 1850);
        bookRepository.save(bookGranto);


    }

}



