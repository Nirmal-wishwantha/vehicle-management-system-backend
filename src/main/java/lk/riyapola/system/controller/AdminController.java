package lk.riyapola.system.controller;

import lk.riyapola.system.dto.ReservationDto;
import lk.riyapola.system.dto.ReservationResponseDto;
import lk.riyapola.system.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @PostMapping("/approve/{id}")
    public ResponseEntity<ReservationResponseDto> approveReserve(@PathVariable Integer id){
        ReservationResponseDto approve = adminService.approve(id);
        return new ResponseEntity<>(approve,HttpStatus.OK);
    }



    @PostMapping("/reject/{id}")
    public ResponseEntity<ReservationResponseDto> approveReject(@PathVariable Integer id){
        ReservationResponseDto reject = adminService.reject(id);
        return new ResponseEntity<>(reject,HttpStatus.OK);
    }



    @GetMapping("/approve/get")
    public List<ReservationDto> approve(){
        List<ReservationDto> approveList = adminService.approve();
        return approveList;
    }

    @GetMapping("/reject/get")
    public List<ReservationDto> approveAll(){
        List<ReservationDto> approveList = adminService.reject();
        return approveList;
    }



}


