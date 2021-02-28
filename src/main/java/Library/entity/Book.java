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
    private UUID uuid;
    @ManyToOne
    private Author author;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String genre;
    @Column(nullable = false)
    private Instant releaseDate;
    @OneToOne
    private BookBorrowing bookBorrowing;
    @OneToOne
    private Reservation reservation;


  //  private String language;
  //  private String publishingHouse;
  //  private Integer numberOfPages;
  //  private String format;


    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)
                    .withLocale(Locale.forLanguageTag("LT"))
                    .withZone(ZoneId.systemDefault());

    public Book(Author author, String title, String genre, Instant releaseDate) {
        this.author = author;
        this.title = title;
        this.genre = genre;
        this.releaseDate = releaseDate;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
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

    public Instant getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Instant releaseDate) {
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
                "uuid=" + uuid +
                ", author=" + author +
                ", title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", releaseDate=" + FORMATTER.format(releaseDate)  +
                ", bookBorrowing=" + bookBorrowing +
                ", reservation=" + reservation +
                '}';
    }
}

