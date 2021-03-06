package Library.repository;

import java.util.List;
import java.util.Set;


public interface CrudRepository<T, ID> {

   List<T> findAll();

    void save(T entity);

}