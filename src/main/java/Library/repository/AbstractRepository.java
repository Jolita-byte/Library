package Library.repository;

import Library.entity.Author;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public abstract class AbstractRepository<T, ID> implements CrudRepository<T, ID> {

    protected final EntityManager entityManager;
    private final Class<? extends T> entityClass;

    public AbstractRepository(EntityManager entityManager, Class<? extends T> entityClass) {
        this.entityManager = entityManager;
        this.entityClass = entityClass;
    }


     @Override
    public void save(T entity) {
        EntityTransaction transaction = entityManager.getTransaction();
        boolean isTransactionActive = transaction.isActive();
        if (!isTransactionActive) {
            transaction.begin();
        }

         entityManager.merge(entity);
        if (!isTransactionActive) {
            transaction.commit();
        }
    }


}