package Library.repository.Book;

import Library.entity.Author;
import Library.entity.Book;
import Library.repository.AbstractRepository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class BookRepository extends AbstractRepository<Book,UUID> {
    public BookRepository(EntityManager entityManager) {
        super(entityManager, Book.class);
    }

    @Override
    public List<Book> findAll() {
        return entityManager.createQuery("FROM Book", Book.class).getResultList();
    }



    public Book findTitle(Book title) {
        return entityManager.find(Book.class, title);
    }

    public List<Book> findBookByTitle(Book title) {
        Query query = entityManager.createQuery("FROM Book WHERE title = :title", Book.class);
        query.setParameter("title", title);
        return query.getResultList();
    }



    public Book find(Author author) {
        return entityManager.find(Book.class, author);
    }


    public List<Book> findBookByAuthor(Author author) {
       Query query = entityManager.createQuery("FROM Book WHERE author = :author", Book.class);
                   query.setParameter("author", author);
                   return query.getResultList();
    }


}


