package lk.riyapola.system.controller;

import lk.riyapola.system.dto.LoginDto;
import lk.riyapola.system.dto.ReservationDto;
import lk.riyapola.system.dto.ResponseDto;
import lk.riyapola.system.dto.UserDto;
import lk.riyapola.system.repo.ReservationRepo;
import lk.riyapola.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    private ReservationRepo reservationRepo;

    @PostMapping("/login")
    public ResponseEntity<LoginDto> login(@RequestBody UserDto userDto){
        LoginDto login = userService.login(userDto);
        return new ResponseEntity<>(login, HttpStatus.OK);
    }



    @PostMapping("/register")
    public ResponseEntity<ResponseDto> register(@RequestBody UserDto userDto){
        ResponseDto register = userService.register(userDto);
        return new ResponseEntity<>(register, HttpStatus.CREATED);
    }

    @GetMapping("/reservation/{id}")
    public ResponseEntity<List<ReservationDto>> getUserReservation(@PathVariable Integer id){

        List<ReservationDto> allReserve = userService.getAllReserve(id);
        return new ResponseEntity<>(allReserve, HttpStatus.OK);

    }
}
