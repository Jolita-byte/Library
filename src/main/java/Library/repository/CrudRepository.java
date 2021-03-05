package Library.repository;

import java.util.List;
import java.util.Set;


public interface CrudRepository<T, ID> {

//    void create(T entity); // CREATE

    T find(ID id);         // READ

   List<T> findAll();

    void save(T entity);   // UPDATE

    void delete(T entity); // DELETE
}