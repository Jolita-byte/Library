package Library.Repository;

import Library.entity.Reader;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.UUID;

public class ReaderRepository extends AbstractRepository<Reader, UUID>{
    public ReaderRepository(EntityManager entityManager) {
        super(entityManager, Reader.class);
    }

    @Override
    public List<Reader> findAll() {
        return entityManager.createQuery("FROM Readers", Reader.class).getResultList();
    }
}


