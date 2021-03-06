package Library.repository.Book;


import Library.entity.Author;
import Library.entity.Book;
import Library.repository.book.BookRepository;
import org.hibernate.Transaction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class BookRepositoryTest {
    @Mock
    private EntityManager entityManager;

    @Mock
    private TypedQuery typedQuery;

    @Mock
    private Book book;

    @InjectMocks
    private BookRepository bookRepository;

    @Test
    public void saveBook() {
        Transaction transaction = Mockito.mock(Transaction.class);
        given(entityManager.getTransaction()).willReturn(transaction);
        given(transaction.isActive()).willReturn(false);

        bookRepository.save(book);

        then(entityManager).should().merge(book);
        then(transaction).should().begin();
        then(transaction).should().commit();
    }

    @Test
    void findAllBooks() {
        given(entityManager.createQuery("FROM Book", Book.class)).willReturn(typedQuery);

        bookRepository.findAll();

        then(typedQuery).should().getResultList();
    }

    @Test
    void findBookByTitleString() {
        given(entityManager.createQuery("FROM Book", Book.class)).willReturn(typedQuery);

        bookRepository.findBookByTitle("Test");

        then(typedQuery).should().getResultList();
    }


    @Test
    void findBookByAuthorTest() {
        Author author = Mockito.mock(Author.class);
        given(entityManager.createQuery("FROM Book WHERE author = :author", Book.class)).willReturn(typedQuery);

        bookRepository.findBookByAuthor(author);

        then(typedQuery).should().getResultList();
    }

    @Test
    void searchByKeyWord() {
        given(entityManager.createQuery("FROM Author WHERE title LIKE :keyWord" +
                " OR author LIKE :keyWord", Book.class)).willReturn(typedQuery);

        bookRepository.searchByKeyWord("Test");

        then(typedQuery).should().getResultList();
    }

    @Test
    public void deleteBook() {
        bookRepository.delete(book);

        then(entityManager).should().remove(book);
    }
}