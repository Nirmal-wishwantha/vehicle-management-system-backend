package lk.riyapola.system.service;

import lk.riyapola.system.Util.JwtAuthentication;
import lk.riyapola.system.dto.LoginDto;
import lk.riyapola.system.dto.ReservationDto;
import lk.riyapola.system.dto.ResponseDto;
import lk.riyapola.system.dto.UserDto;
import lk.riyapola.system.entity.Reservation;
import lk.riyapola.system.entity.User;
import lk.riyapola.system.repo.ReservationRepo;
import lk.riyapola.system.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    JwtAuthentication jwtAuthentication;

    @Autowired
    ReservationRepo reservationRepo;

    public LoginDto login(UserDto userDto){

        Optional<User> user = userRepo.getUserByEmail(userDto.getEmail());

        byte[] decodedBytes = Base64.getDecoder().decode(user.get().getPassword());
        String decodedPassword = new String(decodedBytes);

        if(userDto.getPassword().equals(decodedPassword)){
            String token = jwtAuthentication.generateJwtAuthentication(user.get());
            return new LoginDto(user.get().getId(),user.get().getEmail(), "Login successful",token);
        }
        return new LoginDto(user.get().getEmail(),"Login failed",null);
    }



    public ResponseDto register(UserDto userDto){

        String encodedPassword = Base64.getEncoder().encodeToString(userDto.getPassword().getBytes());

        User save = userRepo.save(new User(userDto.getName(), userDto.getPhone(), userDto.getEmail(), encodedPassword));

        return new ResponseDto(save.getEmail(),"register Successful");

    }


    public List<ReservationDto> getAllReserve(Integer id){

        List<Reservation> byUserId = reservationRepo.findByUserId(id);

        List<ReservationDto> userList= new ArrayList<>();

        for(Reservation reservation : byUserId){
            ReservationDto dto=new ReservationDto();

            dto.setReservationDate(reservation.getReservationDate());
            dto.setReservationEmail(reservation.getReservationEmail());
            dto.setPickupTime(reservation.getPickupTime());
            dto.setPhoneNumber(reservation.getPhoneNumber());
            dto.setVehicleId(reservation.getVehicle().getId());
            dto.setReservationStatus(reservation.getStatus());

            userList.add(dto);
        }
        return userList;

    }
}
