package Library.repository.Author;

import Library.entity.*;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityManager;


import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static java.util.Collections.singletonList;

@ExtendWith(MockitoExtension.class)
class AuthorRepositoryTest {
    @Mock
    private EntityManager entityManager;

    @Mock
    private Transaction transaction;

    //@Mock
   // Query query;

    @Mock
    private Author author;

    @InjectMocks
    private AuthorRepository authorRepository;

    @Test
    public void saveAuthor() {
        given(entityManager.getTransaction()).willReturn(transaction);
        //author = new Author("Jonas", "Jonaitis");
        authorRepository.save(author);

        then(entityManager).should().merge(author);
    }

 /*   @Test
    public void findAllAuthors(){
        query = Mockito.mock(Query.class);
        authorRepository.findAll();

        then(entityManager).should().createQuery(query.toString(), Author.class).getResultList();
    }

    public void findAuthorbyGivenID(){}*/


/*    @Test
    public void searchesForReservations() {
        defaultReservationDao.findReservationsFor(new Car(), new Date(), new Date()); // <- we call the method we are testing

        then(entityManager).should().createQuery(); // <- Mockito's then method makes sure that when we called the method above, entityManager.find() was called
    }*/

}

/*    @Test
    public void savesReservationList() {
        defaultReservationDao.saveAll(singletonList(reservation)); // <- we call the method we are testing

        then(entityManager).should().persist(singletonList(reservation)); // <- Mockito's then method makes sure that when we called the method above, entityManager.persist() was called
    }*/

/**
 * Third test for the implementation
 * Makes sure that if the find method is called, that the fake entity manager will call the find method to find the data needed
 * It does not care how it does it or what it does, it only needs to know that it was done.
 * In another implementation it might be needed to check that the result is equal to what was found, but in our implementation, it does not return the found value
 * it instead returns an empty list.
 */
/*    @Test
    public void searchesForReservations() {
        defaultReservationDao.findReservationsFor(new Car(), new Date(), new Date()); // <- we call the method we are testing

        then(entityManager).should().find(); // <- Mockito's then method makes sure that when we called the method above, entityManager.find() was called
    }*/
