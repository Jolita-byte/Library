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
@Table(name = "reservations")
public class Reservation {

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)
                    .withLocale(Locale.forLanguageTag("LT"))
                    .withZone(ZoneId.systemDefault());

    @Id
    @GeneratedValue()
    private UUID id;

    @OneToOne
    private Book book;

    @ManyToOne
    private Reader reader;

    @Column(nullable = false)
    private Instant beginDate;

    @Column(nullable = false)
    private Instant endDate;

    public Reservation(Book book, Reader reader, Instant beginDate) {
        this.book = book;
        this.reader = reader;
        this.beginDate = beginDate;
        this.endDate = beginDate.plus(3, ChronoUnit.DAYS);
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

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public Instant getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Instant beginDate) {
        this.beginDate = beginDate;
    }

    public Instant getEndDate() {
        return endDate;
    }

    public void setEndDate(Instant endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", book=" + book +
                ", reader=" + reader +
                ", beginDate=" + FORMATTER.format(beginDate) +
                ", endDate=" + FORMATTER.format(endDate)+
                '}';
    }
}
