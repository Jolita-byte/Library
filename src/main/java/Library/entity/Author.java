package Library.entity;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    //@OneToOne
    //private Book book;
  
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Book> books = new ArrayList<>();

    public Author(){}


    public Author(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBook(List<Book> books) {
        this.books = books;
    }

   /* public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }*/


    @Override
    public String toString() {
        return "Author{" +
               "id" + id +":" +
               "name='" + name + '\'' +
               ", surname='" + surname + '\'' +
                '}';
    }
}
