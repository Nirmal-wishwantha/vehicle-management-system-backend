package lk.riyapola.system.repo;

import lk.riyapola.system.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Integer> {

   Optional<User> getUserByEmail(String email);
}
