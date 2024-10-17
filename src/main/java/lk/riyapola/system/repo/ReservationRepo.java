package lk.riyapola.system.repo;

import lk.riyapola.system.entity.Reservation;
import lk.riyapola.system.entity.Vehicle;
import lk.riyapola.system.status.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;


public interface ReservationRepo extends JpaRepository<Reservation, Integer> {

    public abstract Reservation findByVehicleAndReservationDate(Vehicle vehicle, LocalDate reservationDate);

    List<Reservation> findByStatus(ReservationStatus status);
}

