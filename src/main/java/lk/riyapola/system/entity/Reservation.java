package lk.riyapola.system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate reservationDate;
    private String reservationEmail;

    // Many reservations can be associated with one user (ManyToOne)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id") // Foreign key reference
    private User userId;  // Assuming you have a User entity

    // Many reservations can be associated with one vehicle (ManyToOne)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id") // Foreign key reference
    private Vehicle vehicleId;  // Assuming you have a Vehicle entity

    public Reservation(Integer id, LocalDate reservationDate, String reservationEmail, Integer id1, Integer id2) {
    }


    public Reservation(LocalDate reservationDate, Integer vehicleId) {
    }
}
