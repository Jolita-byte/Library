package Library.repository.author;

import Library.entity.Author;
import Library.repository.AbstractRepository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.UUID;

public class AuthorRepository extends AbstractRepository<Author, UUID> {

    public AuthorRepository(EntityManager entityManager) {
        super(entityManager, Author.class);
    }

    @Override
    public List<Author> findAll() {
        return  entityManager.createQuery("FROM Author", Author.class).getResultList();
    }

    public List<Author> searchByKeyWord(String keyWord) {
        Query query = entityManager.createQuery("FROM Author WHERE name LIKE :keyWord" +
                                                " OR surname LIKE :keyWord", Author.class);
        query.setParameter("keyWord", "%" + keyWord + "%");
        return query.getResultList();
    }
}
