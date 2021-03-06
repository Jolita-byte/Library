package Library.repository.book;

import Library.entity.Author;
import Library.entity.Book;
import Library.repository.AbstractRepository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.UUID;

public class BookRepository extends AbstractRepository<Book, UUID> {

    public BookRepository(EntityManager entityManager) {
        super(entityManager, Book.class);
    }

    @Override
    public List<Book> findAll() {
        return entityManager.createQuery("FROM Book", Book.class).getResultList();
    }


    public List<Book> findBookByTitle(String title) {
        Query query = entityManager.createQuery("FROM Book WHERE title = :title", Book.class);
        query.setParameter("title", "%" + title + "%");
        return query.getResultList();
    }


    public List<Book> findBookByAuthor(Author author) {
        Query query = entityManager.createQuery("FROM Book WHERE author = :author", Book.class);
        query.setParameter("author", "%" + author + "%");
        return query.getResultList();
    }


    public List<Book> searchByKeyWord(String keyWord) {
        Query query = entityManager.createQuery("FROM Author WHERE title LIKE :keyWord" +
                                                " OR author LIKE :keyWord", Book.class);
        query.setParameter("keyWord", "%" + keyWord + "%");
        return query.getResultList();
    }
}


