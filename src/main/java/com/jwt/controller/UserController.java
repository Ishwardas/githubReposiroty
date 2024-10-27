package com.jwt.controller;

import com.jwt.entity.AppUser;
import com.jwt.payload.LoginDto;
import com.jwt.repository.AppUserRepository;
import com.jwt.service.AppUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private AppUserRepository appUserRepository;
    private AppUserService appuserService;


    public UserController(AppUserRepository appUserRepository, AppUserService appuserService) {
        this.appUserRepository = appUserRepository;
        this.appuserService = appuserService;
    }
    @PostMapping("/signup")
    public ResponseEntity<?>createUser(@RequestBody AppUser user) {
        Optional<AppUser> opUsername = appUserRepository.findByUsername(user.getUsername());
        if (opUsername.isPresent()) {
            return new ResponseEntity<>("this username is allready is present", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Optional<AppUser> opEmail = appUserRepository.findByEmail(user.getEmail());
        if (opEmail.isPresent()) {
            return new ResponseEntity<>("this email is allready present", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        AppUser proSaved = appuserService.createProfile(user);
        return new ResponseEntity<>(proSaved,HttpStatus.CREATED);

    }
    @GetMapping("/message")
    public String getMessage(){
        return "hello";
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto dto){
        String token = appuserService.verifyLogin(dto);
        return new ResponseEntity<>(token,HttpStatus.FORBIDDEN);
    }
}
