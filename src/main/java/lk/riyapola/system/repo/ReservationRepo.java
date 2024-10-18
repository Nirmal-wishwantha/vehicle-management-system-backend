package lk.riyapola.system.repo;

import lk.riyapola.system.entity.Reservation;
import lk.riyapola.system.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;



public interface ReservationRepo extends JpaRepository<Reservation, Integer> {

Reservation findById(int id);

}
