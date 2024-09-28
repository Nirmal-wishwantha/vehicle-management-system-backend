package lk.riyapola.system.service;

import lk.riyapola.system.dto.ReservationDto;
import lk.riyapola.system.dto.ReservationResponseDto;
import lk.riyapola.system.entity.Reservation;
import lk.riyapola.system.entity.User;
import lk.riyapola.system.entity.Vehicle;
import lk.riyapola.system.repo.ReservationRepo;
import lk.riyapola.system.repo.UserRepo;
import lk.riyapola.system.repo.VehicleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class ReservationService {

    @Autowired
    private ReservationRepo reservationRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private VehicleRepo vehicleRepo;

    public ReservationResponseDto reserve(ReservationDto reservationDto, Integer vehicleId) {


            Vehicle vehicle = vehicleRepo.findById(vehicleId)
                    .orElseThrow(() -> new RuntimeException("Vehicle not found with ID: " + vehicleId));


            User user = userRepo.getUserByEmail(reservationDto.getReservationEmail())
                    .orElseThrow(() -> new RuntimeException("User not found with email: " + reservationDto.getReservationEmail()));

            Reservation reservation = new Reservation();
            reservation.setReservationDate(reservationDto.getReservationDate());
            reservation.setReservationEmail(reservationDto.getReservationEmail());
            reservation.setPhoneNumber(reservationDto.getPhoneNumber());
            reservation.setPickupTime(reservationDto.getPickupTime());
            reservation.setUserId(user);
            reservation.setVehicleId(vehicle);

            Reservation savedReservation = reservationRepo.save(reservation);

            return new ReservationResponseDto(savedReservation.getReservationEmail(), savedReservation.getReservationDate(), "Reservation successful!");

    }

    public List<ReservationDto> getReservation() {
        List<Reservation> all = reservationRepo.findAll();
        List<ReservationDto> allReservation = new ArrayList<>();

        for (Reservation reservation : all) {
            allReservation.add(new ReservationDto(reservation.getId(),reservation.getReservationDate(),reservation.getReservationEmail(),
                    reservation.getPickupTime(),reservation.getPhoneNumber(),reservation.getVehicleId().getId()));
        }
        return allReservation;
    }

    public boolean deleteReservation(Integer reservationId) {
        if (reservationRepo.existsById(reservationId)) {
            reservationRepo.deleteById(reservationId);
            return true;
        }
        return false;
    }



    public ReservationDto updateReservation(ReservationDto reservationDto, Integer reservationId) {
        // Retrieve the existing reservation by ID
        Optional<Reservation> findReservation = reservationRepo.findById(reservationId);

        if (findReservation.isPresent()) {
            // Update the reservation details
            Reservation reservation = findReservation.get();
            reservation.setReservationDate(reservationDto.getReservationDate());

            reservation.setVehicleId(vehicleRepo.findById(reservationDto.getVehicleId())
                    .orElseThrow(() -> new IllegalArgumentException("Vehicle not found")));

            // Save the updated reservation
            Reservation updatedReservation = reservationRepo.save(reservation);

            // Return the updated ReservationDto
            return new ReservationDto(
                    updatedReservation.getReservationDate(),
                    updatedReservation.getReservationEmail(),
                    updatedReservation.getVehicleId().getId()

            );
        }
        return null;
    }




}
