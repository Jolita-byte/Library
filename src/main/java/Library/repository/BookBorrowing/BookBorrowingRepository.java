package Library.repository.BookBorrowing;

import Library.entity.BookBorrowing;
import Library.repository.AbstractRepository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.UUID;

public class BookBorrowingRepository extends AbstractRepository<BookBorrowing, UUID> {

    public BookBorrowingRepository(EntityManager entityManager) {
        super(entityManager, BookBorrowing.class);
    }

    @Override
    public List findAll() {
        return null;
    }

}
