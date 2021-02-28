package Library;


import Library.entity.*;
import Library.repository.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;

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
    }


    public void run() {
    }

    private void print() {
    }

}
