package lk.riyapola.system.repo;

import lk.riyapola.system.entity.Reservation;
import lk.riyapola.system.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;


public interface ReservationRepo extends JpaRepository<Reservation, Integer> {

    public abstract Reservation findByVehicleAndReservationDate(Vehicle vehicle, LocalDate reservationDate);

}

