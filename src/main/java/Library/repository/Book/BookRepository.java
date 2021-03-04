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

    public List<Book> searchByNameFragment(String fragment) {
        Query query = entityManager.createQuery("FROM Book WHERE name LIKE :nameFragment", Book.class);
        query.setParameter("nameFragment", "%" + fragment + "%");
        return query.getResultList();
    }



//    public List<Book> findBookByAuthor(Author author) {
//       Query query = entityManager.createQuery("FROM Book WHERE authors = :author", Book.class);
//                   query.setParameter("author", author);
//                   return query.getResultList();
//    }


}


