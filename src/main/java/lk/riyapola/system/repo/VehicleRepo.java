package lk.riyapola.system.repo;

import lk.riyapola.system.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepo extends JpaRepository <Vehicle, Integer>{

    Vehicle findById(int id);
}
