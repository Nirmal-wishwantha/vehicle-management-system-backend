package lk.riyapola.system.service;

import lk.riyapola.system.dto.ReservationDto;
import lk.riyapola.system.dto.ReservationResponseDto;
import lk.riyapola.system.entity.Reservation;
import lk.riyapola.system.entity.User;
import lk.riyapola.system.entity.Vehicle;
import lk.riyapola.system.repo.ReservationRepo;
import lk.riyapola.system.repo.UserRepo;
import lk.riyapola.system.repo.VehicleRepo;
import lk.riyapola.system.status.ReservationStatus;
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

        // Retrieve the vehicle by ID
        Vehicle vehicleFind = vehicleRepo.findById(vehicleId)
                .orElseThrow(() -> new RuntimeException("Vehicle not found with ID: " + vehicleId));

        Reservation existingReservation = reservationRepo.findByVehicleAndReservationDate( vehicleFind,reservationDto.getReservationDate());

        if (existingReservation != null) {
            // If the reservation exists, return a failure response
            return new ReservationResponseDto(reservationDto.getReservationEmail(),
                    reservationDto.getReservationDate(),
                    "Reservation not successful: another reservation exists on this date for this vehicle!");
        }

        // Retrieve the user by email, or throw an exception if not found
        User user = userRepo.getUserByEmail(reservationDto.getReservationEmail())
                .orElseThrow(() -> new RuntimeException("User not found with email: " +
                        reservationDto.getReservationEmail()));

        // Create a new Reservation object and populate it with details
        Reservation reservation = new Reservation();
        reservation.setReservationDate(reservationDto.getReservationDate());
        reservation.setReservationEmail(reservationDto.getReservationEmail());
        reservation.setPhoneNumber(reservationDto.getPhoneNumber());
        reservation.setPickupTime(reservationDto.getPickupTime());
        reservation.setUser(user);
        reservation.setVehicle(vehicleFind);
        reservation.setStatus(ReservationStatus.PENDING);

        // Save the reservation to the database
        Reservation savedReservation = reservationRepo.save(reservation);

        // Return a success response with reservation details
        return new ReservationResponseDto(savedReservation.getReservationEmail(),
                savedReservation.getReservationDate(),savedReservation.getStatus(), "Reservation successful!");
    }



    public List<ReservationDto> getReservation() {
        List<Reservation> all = reservationRepo.findAll();
        List<ReservationDto> allReservation = new ArrayList<>();

        for (Reservation reservation : all) {
            allReservation.add(new ReservationDto(reservation.getId(),reservation.getReservationDate(),reservation.getReservationEmail(),
                    reservation.getPickupTime(),reservation.getPhoneNumber(),reservation.getVehicle().getId()));
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
            reservation.setPhoneNumber(reservationDto.getPhoneNumber());
            reservation.setPickupTime(reservationDto.getPickupTime());

            reservation.setVehicle(vehicleRepo.findById(reservationDto.getVehicleId())
                    .orElseThrow(() -> new IllegalArgumentException("Vehicle not found")));

            Reservation updatedReservation = reservationRepo.save(reservation);

            return new ReservationDto(
                    updatedReservation.getReservationDate(),
                    updatedReservation.getReservationEmail(),
                    updatedReservation.getPickupTime(),
                    updatedReservation.getPhoneNumber(),
                    updatedReservation.getVehicle().getId()

            );
        }
        return null;
    }




}
