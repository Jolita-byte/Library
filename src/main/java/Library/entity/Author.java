package Library.entity;

import javax.persistence.*;
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



    public Author(String name, String surname, Book book) {
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



    @Override
    public String toString() {
        return "Author{" +
               "name='" + name + '\'' +
               ", surname='" + surname + '\'' +
                '}';
    }
}
