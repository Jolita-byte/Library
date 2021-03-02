package Library;


import Library.entity.*;
import Library.repository.*;
import Library.repository.Author.AuthorRepository;
import Library.repository.Book.BookRepository;
import Library.repository.BookBorrowing.BookBorrowingRepository;
import Library.repository.Reader.ReaderRepository;
import Library.repository.Reservation.ReservationRepository;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.Instant;
import java.util.List;

public class Project {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final ReaderRepository readerRepository;
    private final ReservationRepository reservationRepository;
    private final BookBorrowingRepository bookBorrowingRepository;


    public Project() {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Author.class)
                .addAnnotatedClass(Book.class)
                .addAnnotatedClass(Reader.class)
                .addAnnotatedClass(Reservation.class)
                .addAnnotatedClass(BookBorrowing.class)
                .buildSessionFactory();

        EntityManager entityManager = sessionFactory.createEntityManager();

        authorRepository = new AuthorRepository(entityManager);
        bookRepository = new BookRepository(entityManager);
        readerRepository = new ReaderRepository(entityManager);
        reservationRepository = new ReservationRepository(entityManager);
        bookBorrowingRepository = new BookBorrowingRepository(entityManager);

        LocalDate date1 = LocalDate.of(2021, 02,05);
        date1.plusDays(10);

    }
    public void run() {
        Author author1 = new Author("Levas", "Tolstojus");
        Book book1 = new Book(author1, "Ana Karenina", "Romanas", 1877);

        Author author2 = new Author("Džeromas Deividass", "Selindžeris");
        Book book2 = new Book(author2, "Rugiuose prie bedugnės", "Romanas", 1951);

        Author author3 = new Author("Džeromas Deividas", "Selindžeris");
        Book book3 = new Book(author3, "Rugiuose prie bedugnės", "Romanas", 1951);

        Author author4 = new Author("Katherine", " Paterson");
        Book book4 = new Book(author4, "Tiltas į terabitiją", "Romanas", 2018);

        Author authorMan = new Author("Tomas","Manas");
        authorRepository.save(authorMan);

        Book bookKazkoks = new Book(authorMan, "Kazkoks","mistika", 1912);
        bookRepository.save(bookKazkoks);

        Author authorLindgren = new Author("Astrid","Lindgren");
        authorRepository.save(authorLindgren);

        Book bookKalis = new Book(authorLindgren, "Kalis Bliunkvistas","vaikams", 1950);
        bookRepository.save(bookKalis);
        Book bookKalis2 = new Book(authorLindgren, "Kalis Bliunkvistas ir Rasmusas","vaikams", 1960);
        bookRepository.save(bookKalis2);
        Book bookPepe = new Book(authorLindgren, "Pepe","vaikams", 1965);
        bookRepository.save(bookPepe);
        Book bookRonja = new Book(authorLindgren, "Ronja plesiko dukte","vaikams", 1970);
        bookRepository.save(bookRonja);
        Author authorVernas = new Author("Ziulis","Vernas");
        authorRepository.save(authorVernas);
        Book bookMenulis = new Book(authorVernas, "Kelione i Menuli","nuotykiu", 1870);
        bookRepository.save(bookMenulis);
        Book book1000 = new Book(authorVernas, "100 myliu po vandeniu","nuotykiu", 1880);
        bookRepository.save(book1000);
        Book bookGranto = new Book(authorVernas, "Kapitono Granto vaikai","nuotykiu", 1850);
        bookRepository.save(bookGranto);
        //System.out.println(bookKazkoks );


    }


    public void printAllBooks() {
        System.out.println("--------------Visos knygos------------------");
        System.out.println(bookRepository.findAll());

    }
    public void printAllAuthors() {
        System.out.println("--------------Visi autoriai ------------------");
        System.out.println(authorRepository.findAll());

    }
    }


