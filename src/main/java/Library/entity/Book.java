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

    @OneToOne
    private Author author;

    @Column(nullable = false)
    private String title;

    private String genre;

    @Column(nullable = false)
    private Instant releaseDate;

  //  private String language;
    //  private String publishingHouse;
  //  private Integer numberOfPages;
 //   private String format;


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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Book{" +
               "author=" + author +
               ", title='" + title + '\'' +
               ", genre='" + genre + '\'' +
               ", releaseDate=" + FORMATTER.format(releaseDate)  +
               '}';
    }
}

