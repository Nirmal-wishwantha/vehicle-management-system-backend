package lk.riyapola.system.service;

import lk.riyapola.system.dto.ReservationDto;
import lk.riyapola.system.dto.ReservationResponseDto;
import lk.riyapola.system.entity.Reservation;
import lk.riyapola.system.repo.ReservationRepo;
import lk.riyapola.system.status.ReservationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class AdminService {

    @Autowired
    ReservationRepo reservationRepo;

    public ReservationResponseDto approve(Integer id){
        Optional<Reservation> findReserve = reservationRepo.findById(id);

        if(findReserve.isPresent()){
            Reservation reserve = findReserve.get();
            reserve.setStatus(ReservationStatus.APPROVED);
            Reservation save = reservationRepo.save(reserve);
            return new ReservationResponseDto(save.getReservationEmail(),save.getReservationDate(),
                    save.getStatus(),"Vehicle Reservation Approved");
        }
       return new ReservationResponseDto(findReserve.get().getReservationEmail(),findReserve.get().getReservationDate(),
               "Your Reservation is Not Approved");
    }



    public ReservationResponseDto reject(Integer id){
        Optional<Reservation> findReserve = reservationRepo.findById(id);
        if(findReserve.isPresent()){
            Reservation reserve = findReserve.get();
            reserve.setStatus(ReservationStatus.REJECTED);
            Reservation save = reservationRepo.save(reserve);
            return new ReservationResponseDto(save.getReservationEmail(),save.getReservationDate(),
                    save.getStatus(),"Your Reservation is Reject");
        }
        return new ReservationResponseDto(findReserve.get().getReservationEmail(),findReserve.get().getReservationDate(),
                findReserve.get().getStatus(),"Your Reservation is Not Rejected");
    }


    public List<ReservationDto> approve(){
        List<Reservation> findlist = reservationRepo.findByStatus(ReservationStatus.APPROVED);

        List<ReservationDto> approveList = new ArrayList<>();

        for(Reservation reservation : findlist){

            ReservationDto dto = new ReservationDto();

            dto.setId(reservation.getId());
            dto.setReservationEmail(reservation.getReservationEmail());
            dto.setReservationDate(reservation.getReservationDate());
            dto.setPhoneNumber(reservation.getPhoneNumber());
            dto.setPickupTime(reservation.getPickupTime());
            dto.setVehicleId(reservation.getVehicle().getId());
            dto.setReservationStatus(reservation.getStatus());

            approveList.add(dto);

        }
        return approveList;

    }

    public List<ReservationDto> reject(){
        List<Reservation> findlist = reservationRepo.findByStatus(ReservationStatus.REJECTED);
        List<ReservationDto> rejectList = new ArrayList<>();
        for(Reservation reservation : findlist){
            ReservationDto dto = new ReservationDto();
            dto.setId(reservation.getId());
            dto.setReservationEmail(reservation.getReservationEmail());
            dto.setReservationDate(reservation.getReservationDate());
            dto.setPhoneNumber(reservation.getPhoneNumber());
            dto.setPickupTime(reservation.getPickupTime());
            dto.setVehicleId(reservation.getVehicle().getId());
            dto.setReservationStatus(reservation.getStatus());
            rejectList.add(dto);
        }
        return rejectList;
    }
}
