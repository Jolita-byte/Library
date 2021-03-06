package Library.repository.reader;

import Library.entity.Reader;
import Library.repository.AbstractRepository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.UUID;

public class ReaderRepository extends AbstractRepository<Reader, UUID> {
    public ReaderRepository(EntityManager entityManager) {
        super(entityManager, Reader.class);
    }

    @Override
    public List<Reader> findAll() {
        return entityManager.createQuery("FROM Readers", Reader.class).getResultList();
    }

    public List<Reader> searchByKeyWord(String keyWord) {
        Query query = entityManager.createQuery("FROM Author WHERE name LIKE :keyWord" +
                                                " OR surname LIKE :keyWord", Reader.class);
        query.setParameter("keyWord", "%" + keyWord + "%");
        return query.getResultList();
    }
}


