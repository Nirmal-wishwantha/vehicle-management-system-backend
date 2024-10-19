package lk.riyapola.system.dto;

import lk.riyapola.system.status.ReservationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDto {

    private Integer id;
    private LocalDate reservationDate;
    private String reservationEmail;
    private LocalTime pickupTime;
    private String phoneNumber;
    private Integer vehicleId;
    private ReservationStatus reservationStatus;


    public ReservationDto(LocalDate reservationDate, String reservationEmail, LocalTime pickupTime, String phoneNumber, Integer vehicleId) {
        this.reservationDate = reservationDate;
        this.reservationEmail = reservationEmail;
        this.pickupTime = pickupTime;
        this.phoneNumber = phoneNumber;
        this.vehicleId = vehicleId;
    }

    public ReservationDto(Integer id, LocalDate reservationDate, String reservationEmail, LocalTime pickupTime, String phoneNumber, Integer vehicleId) {
        this.id = id;
        this.reservationDate = reservationDate;
        this.reservationEmail = reservationEmail;
        this.pickupTime = pickupTime;
        this.phoneNumber = phoneNumber;
        this.vehicleId = vehicleId;
    }
}
