package Library.repository.author;

import Library.entity.*;
import org.hibernate.Transaction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class AuthorRepositoryTest {
    @Mock
    private EntityManager entityManager;

    @Mock
    private Author author;

    @InjectMocks
    private AuthorRepository authorRepository;

    @Test
    public void saveAuthor() {
        Transaction transaction = Mockito.mock(Transaction.class);
        given(entityManager.getTransaction()).willReturn(transaction);
        given(transaction.isActive()).willReturn(false);

        authorRepository.save(author);

        then(entityManager).should().merge(author);
        then(transaction).should().begin();
        then(transaction).should().commit();
    }

    @Test
    public void findAllAuthors() {
        TypedQuery typedQuery = Mockito.mock(TypedQuery.class);
        given(entityManager.createQuery("FROM Author", Author.class)).willReturn(typedQuery);

        authorRepository.findAll();

        then(typedQuery).should().getResultList();
    }

    @Test
    public void searchAuthorByKeyWord() {
        TypedQuery typedQuery = Mockito.mock(TypedQuery.class);
        given(entityManager.createQuery("FROM Author WHERE name LIKE :keyWord" +
                " OR surname LIKE :keyWord", Author.class)).willReturn(typedQuery);

        authorRepository.searchByKeyWord("Test");

        then(typedQuery).should().getResultList();
    }

    @Test
    public void deleteAuthor() {
        authorRepository.delete(author);

        then(entityManager).should().remove(author);
    }
}
