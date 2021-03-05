package Library.entity;

import javax.persistence.*;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Author> authors = new HashSet<>();

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String genre;

    @Column(nullable = false)
    private Integer releaseDate;

    @OneToOne
    private BookBorrowing bookBorrowing;

    @OneToOne
    private Reservation reservation;


  //  private String language;
  //  private String publishingHouse;
  //  private Integer numberOfPages;
  //  private String format;


//    private static final DateTimeFormatter FORMATTER =
//            DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)
//                    .withLocale(Locale.forLanguageTag("LT"))
//                    .withZone(ZoneId.systemDefault());

    public Book(Set<Author> authors, String title, String genre, Integer releaseDate) {
        this.authors = authors;
        this.title = title;
        this.genre = genre;
        this.releaseDate = releaseDate;
    }

    public Book(Set<Author> authors, String title) {
        this.authors = authors;
        this.title = title;
    }

    public Book() {

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Integer getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Integer releaseDate) {
        this.releaseDate = releaseDate;
    }

    public BookBorrowing getBookBorrowing() {
        return bookBorrowing;
    }

    public void setBookBorrowing(BookBorrowing bookBorrowing) {
        this.bookBorrowing = bookBorrowing;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    @Override
    public String toString() {
        return "Book{" +
                "uuid=" + id +
                ", author=" + authors +
                ", title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", releaseDate=" + releaseDate +
                ", bookBorrowing=" + bookBorrowing +
                ", reservation=" + reservation +
                '}';
    }
}

