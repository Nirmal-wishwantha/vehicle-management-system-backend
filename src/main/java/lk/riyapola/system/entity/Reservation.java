package lk.riyapola.system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

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
    private String phoneNumber;
    private LocalTime pickupTime;


//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")
//    private User userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id") // Foreign key reference
    private User user;  // Changed from userId to user


//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "vehicle_id")
//    private Vehicle vehicle;

    // Many reservations can be associated with one vehicle (ManyToOne)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id") // Foreign key reference
    private Vehicle vehicle;  // Changed from vehicleId to vehicle

    public Reservation(Integer id, LocalDate reservationDate, String reservationEmail, Integer id1, Integer id2) {
    }


    public Reservation(LocalDate reservationDate, Integer vehicleId) {
    }
}
