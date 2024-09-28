package lk.riyapola.system.dto;

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

    public ReservationDto(LocalDate reservationDate, String reservationEmail, Integer vehicleId) {
        this.reservationDate = reservationDate;
        this.reservationEmail = reservationEmail;
        this.vehicleId = vehicleId;
    }
}
