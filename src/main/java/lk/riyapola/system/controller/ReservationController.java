package lk.riyapola.system.controller;

import lk.riyapola.system.dto.ReservationDto;
import lk.riyapola.system.dto.ReservationResponseDto;
import lk.riyapola.system.dto.ResponseDto;
import lk.riyapola.system.repo.VehicleRepo;
import lk.riyapola.system.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("reserve")
public class ReservationController {

    @Autowired
    ReservationService reservationService;

    @Autowired
    VehicleRepo vehicleRepo;

    @PostMapping("/{id}")
    public ResponseEntity<ReservationResponseDto> vehicleReserve(@RequestBody ReservationDto reservationDto,
                                                                 @PathVariable Integer id) {

        ReservationResponseDto reserve = reservationService.reserve(reservationDto,id);

        return new ResponseEntity<>(reserve, HttpStatus.CREATED);

    }

    @GetMapping
    public List<ReservationDto> getAllReservations() {
        List<ReservationDto> reservation = reservationService.getReservation();
        return reservation;

    }

    @DeleteMapping("/{id}")
    public String deleteReservation(@PathVariable Integer id) {
        boolean b = reservationService.deleteReservation(id);
        return new String("Reservation deleted");
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservationDto> updateReservation(@RequestBody ReservationDto reservationDto, @PathVariable Integer id){
        ReservationDto updateReservation = reservationService.updateReservation(reservationDto, id);
        return new ResponseEntity<>(updateReservation, HttpStatus.OK);

    }



}
