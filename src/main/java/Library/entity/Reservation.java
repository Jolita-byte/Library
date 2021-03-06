package Library.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Entity
@Table(name = "reservations")
public class Reservation {


    @Id
    @GeneratedValue()
    private UUID id;

    @OneToOne
    private Book book;

    @ManyToOne
    private Reader reader;

    @Column(nullable = false)
    private LocalDate beginDate;

    @Column(nullable = false)
    private LocalDate endDate;

    public Reservation(Book book, Reader reader, LocalDate beginDate) {
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

    public LocalDate getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(LocalDate beginDate) {
        this.beginDate = beginDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", book=" + book +
                ", reader=" + reader +
                ", beginDate=" + beginDate +
                ", endDate=" + endDate +
                '}';
    }
}
