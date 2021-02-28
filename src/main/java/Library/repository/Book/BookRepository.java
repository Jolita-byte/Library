package Library.repository.Book;

import Library.entity.Book;
import Library.repository.AbstractRepository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.UUID;

public class BookRepository extends AbstractRepository<Book,UUID> {
    public BookRepository(EntityManager entityManager) {
        super(entityManager, Book.class);
    }

    @Override
    public List<Book> findAll() {
        return entityManager.createQuery("FROM Book", Book.class).getResultList();
    }
}


