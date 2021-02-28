package Library.repository.Reservation;

import Library.entity.Reservation;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.UUID;

public class ReservationRepository<T, ID> extends Library.repository.AbstractRepository<Reservation, UUID> {

    public ReservationRepository(EntityManager entityManager) {
        super(entityManager, Reservation.class);
    }

    @Override
    public List<Reservation> findAll() {
        return entityManager.createQuery("FROM Reservation", Reservation.class).getResultList();
    }
}
