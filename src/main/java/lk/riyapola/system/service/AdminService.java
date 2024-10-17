package lk.riyapola.system.service;

import lk.riyapola.system.dto.ReservationResponseDto;
import lk.riyapola.system.entity.Reservation;
import lk.riyapola.system.repo.ReservationRepo;
import lk.riyapola.system.status.ReservationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
       return null;
    }





    public  void reject(){

    }
}
