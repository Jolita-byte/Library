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
import Library.utilities.input.DefaultInputReceiver;
import Library.utilities.input.InputReceiver;
import Library.utilities.output.DefaultOutputProducer;
import Library.utilities.output.OutputProducer;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

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
        List<Author> authors1 = new ArrayList<>();
        Author author11 = new Author("Levas", "Tolstojus");
        Author author12 = new Author("Levas2", "Tolstojus2");
        authors1.add(author11);
        authors1.add(author12);
        Book book1 = new Book(authors1, "Ana Karenina", "Romanas", 1877);
        bookRepository.save(book1);


        List<Author> authors2 = new ArrayList<>();
        authors2.add(new Author("Džeromas Deividass", "Selindžeris"));
        Book book2 = new Book(authors2, "Rugiuose prie bedugnės", "Romanas", 1951);
        bookRepository.save(book2);

        List<Author> authors3 = new ArrayList<>();
        authors3.add(new Author("Džeromas Deividas", "Selindžeris"));
        Book book3 = new Book(authors3, "Rugiuose prie bedugnės", "Romanas", 1951);
        bookRepository.save(book3);


        List<Author> authors4 = new ArrayList<>();
        authors4.add(new Author("Katherine", " Paterson"));
        Book book4 = new Book(authors4, "Tiltas į terabitiją", "Romanas", 2018);
        bookRepository.save(book4);

        Author authorMan = new Author("Tomas", "Manas");
        authorRepository.save(authorMan);
        List<Author> authors5 = new ArrayList<>();
        authors5.add(authorMan);
        Book bookKazkoks = new Book(authors5, "Kazkoks", "mistika", 1912);
        bookRepository.save(bookKazkoks);

        Author authorLindgren = new Author("Astrid", "Lindgren");
        authorRepository.save(authorLindgren);
        List<Author> authors6 = new ArrayList<>();
        authors6.add(authorLindgren);
        Book bookKalis = new Book(authors6, "Kalis Bliunkvistas", "vaikams", 1950);
        bookRepository.save(bookKalis);
        Book bookKalis2 = new Book(authors6, "Kalis Bliunkvistas ir Rasmusas", "vaikams", 1960);
        bookRepository.save(bookKalis2);
        Book bookPepe = new Book(authors6, "Pepe", "vaikams", 1965);
        bookRepository.save(bookPepe);
        Book bookRonja = new Book(authors6, "Ronja plesiko dukte", "vaikams", 1970);
        bookRepository.save(bookRonja);

        Author authorVernas = new Author("Ziulis", "Vernas");
        authorRepository.save(authorVernas);
        List<Author> authors7 = new ArrayList<>();
        authors7.add(authorVernas);
        Book bookMenulis = new Book(authors7, "Kelione i Menuli", "nuotykiu", 1870);
        bookRepository.save(bookMenulis);
        Book book1000 = new Book(authors7, "100 myliu po vandeniu", "nuotykiu", 1880);
        bookRepository.save(book1000);
        Book bookGranto = new Book(authors7, "Kapitono Granto vaikai", "nuotykiu", 1850);
        bookRepository.save(bookGranto);

    }

}



