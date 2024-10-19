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

        Vehicle vehicleFind = vehicleRepo.findById(vehicleId)
                .orElseThrow(() -> new RuntimeException("Vehicle not found with ID: " + vehicleId));

        Reservation existingReservation = reservationRepo.findByVehicleAndReservationDate( vehicleFind,reservationDto.getReservationDate());

        if (existingReservation != null) {
            return new ReservationResponseDto(reservationDto.getReservationEmail(),
                    reservationDto.getReservationDate(),
                    "Reservation not successful: another reservation exists on this date for this vehicle!");
        }

        User user = userRepo.getUserByEmail(reservationDto.getReservationEmail())
                .orElseThrow(() -> new RuntimeException("User not found with email: " +
                        reservationDto.getReservationEmail()));

        Reservation reservation = new Reservation();
        reservation.setReservationDate(reservationDto.getReservationDate());
        reservation.setReservationEmail(reservationDto.getReservationEmail());
        reservation.setPhoneNumber(reservationDto.getPhoneNumber());
        reservation.setPickupTime(reservationDto.getPickupTime());
        reservation.setUser(user);
        reservation.setVehicle(vehicleFind);
        reservation.setStatus(ReservationStatus.PENDING);

        Reservation savedReservation = reservationRepo.save(reservation);

        return new ReservationResponseDto(savedReservation.getReservationEmail(),
                savedReservation.getReservationDate(),savedReservation.getStatus(), "Reservation successful!");
    }



    public ReservationResponseDto updateReservation(ReservationDto reservationDto, Integer reservationId) {

        Optional<Reservation> findReservation = reservationRepo.findById(reservationId);

        Vehicle vehicle = vehicleRepo.findById(reservationDto.getVehicleId()).orElseThrow(() -> new RuntimeException
                ("Vehicle not found with ID: " + reservationId));

        Reservation VehicleAndReservationDate = reservationRepo.findByVehicleAndReservationDate(vehicle, reservationDto.getReservationDate());

        if (VehicleAndReservationDate != null) {
            return new ReservationResponseDto(reservationDto.getReservationEmail(),
                    reservationDto.getReservationDate(),
                    "Reservation not successful: another reservation exists on this date for this vehicle!");
        }

        if (findReservation.isPresent()) {
            Reservation updateReservation = findReservation.get();
            updateReservation.setReservationDate(reservationDto.getReservationDate());
            updateReservation.setPickupTime(reservationDto.getPickupTime());
            updateReservation.setPhoneNumber(reservationDto.getPhoneNumber());
            updateReservation.setVehicle(vehicle);
            updateReservation.setStatus(findReservation.get().getStatus());


            Reservation savedReservation = reservationRepo.save(updateReservation);
            return new ReservationResponseDto(savedReservation.getReservationEmail(),savedReservation.getReservationDate(),
                    savedReservation.getStatus(), "Reservation Update successful!");
        }
        return new ReservationResponseDto(findReservation.get().getReservationEmail(),findReservation.get().getReservationDate(),"" +
        "Reservation not successful!");

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

}
