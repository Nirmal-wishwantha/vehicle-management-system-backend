package lk.riyapola.system.dto;

import lk.riyapola.system.status.ReservationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationResponseDto {

    private String email;
    private LocalDate reservationDate;
    private ReservationStatus reservationStatus;
    private String massage;

    public ReservationResponseDto(String email, LocalDate reservationDate, String massage) {
        this.email = email;
        this.reservationDate = reservationDate;
        this.massage = massage;
    }


}
