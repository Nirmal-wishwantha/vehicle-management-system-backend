package lk.riyapola.system.controller;

import lk.riyapola.system.dto.LoginDto;
import lk.riyapola.system.dto.ResponseDto;
import lk.riyapola.system.dto.UserDto;
import lk.riyapola.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public ResponseEntity<LoginDto> login(@RequestBody UserDto userDto){
        LoginDto login = userService.login(userDto);
        return new ResponseEntity<>(login, HttpStatus.OK);
    }

    @PostMapping("/logout")
    public void logout(){

    }


    @PostMapping("/register")
    public ResponseEntity<ResponseDto> register(@RequestBody UserDto userDto){
        ResponseDto register = userService.register(userDto);
        return new ResponseEntity<>(register, HttpStatus.CREATED);
    }
}
