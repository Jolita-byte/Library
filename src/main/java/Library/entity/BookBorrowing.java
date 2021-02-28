package Library.entity;

import javax.persistence.*;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.UUID;

@Entity
@Table(name = "borrowing")
public class BookBorrowing {

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)
                    .withLocale(Locale.forLanguageTag("LT"))
                    .withZone(ZoneId.systemDefault());

    @Id
    @GeneratedValue
    private UUID id;
    @OneToOne
    private Book book;
    @Column (nullable = false)
    private Instant startTerm;
    @Column (nullable = false)
    private Instant endTerm;
    @ManyToOne
    private Reader reader;
    private String comment;

    public BookBorrowing(Book book, Instant startTerm, Reader reader) {

        this.book = book;
        this.startTerm = startTerm;
        this.endTerm = startTerm.plus(30, ChronoUnit.DAYS);
        this.reader = reader;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Instant getStartTerm() {
        return startTerm;
    }

    public void setStartTerm(Instant startTerm) {
        this.startTerm = startTerm;
    }

    public Instant getEndTerm() {
        return endTerm;
    }

    public void setEndTerm(Instant endTerm) {
        this.endTerm = endTerm;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "BookBorrowing{" +
                "id=" + id +
                ", book=" + book +
                ", startTerm=" + FORMATTER.format(startTerm)+ '\'' +
                ", endTerm=" + FORMATTER.format(endTerm) + '\'' +
                ", comment='" + comment + '\'' +
                ", reader=" + reader +
                '}';
    }
}
