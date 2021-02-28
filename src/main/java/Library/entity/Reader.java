package Library.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "readers")

public class Reader {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private Instant activeSince;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Instant getActiveSince() {
        return activeSince;
    }

    public void setActiveSince(Instant activeSince) {
        this.activeSince = activeSince;
    }

    public String toString() {
        return "Reader{" +
                "id=" + id +
                ", name = " + name +
                ", surname = " + surname +
                ", email = " + email +
                ", phone number = " + phoneNumber +
                ", active since = " + activeSince +
                '}';
    }

}

