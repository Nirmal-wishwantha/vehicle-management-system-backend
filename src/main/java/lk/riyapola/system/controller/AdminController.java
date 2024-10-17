package lk.riyapola.system.controller;

import lk.riyapola.system.dto.ReservationResponseDto;
import lk.riyapola.system.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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



    @PostMapping("reject")
    public void  approveReject(){

    }


}


