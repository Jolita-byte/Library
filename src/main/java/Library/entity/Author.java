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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    @Override
    public String toString() {
        return "Author{" +
               "id" + id +":" +
               "name='" + name + '\'' +
               ", surname='" + surname + '\'' +
                '}';
    }
}
