package com.jwt.controller;

import com.jwt.entity.AppUser;
import com.jwt.payload.LoginDto;
import com.jwt.payload.RUserDto;
import com.jwt.payload.TokenDto;
import com.jwt.payload.UserDto;
import com.jwt.repository.AppUserRepository;
import com.jwt.service.AppUserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
    public ResponseEntity<?>createUser(@Valid @RequestBody UserDto userDto, BindingResult result) {
        if (result.hasErrors())
        {
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.NOT_ACCEPTABLE);
        }
        Optional<AppUser> opUsername = appUserRepository.findByUsername(userDto.getUsername());
        if (opUsername.isPresent()) {
            return new ResponseEntity<>("Username is already Registered", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Optional<AppUser> opEmail = appUserRepository.findByEmail(userDto.getEmail());
        if (opEmail.isPresent()) {
            return new ResponseEntity<>("Email is already Registered", HttpStatus.INTERNAL_SERVER_ERROR);
        }
          userDto.setRole("ROLE_USER");
        RUserDto proSaved = appuserService.createProfile(userDto);
        return new ResponseEntity<>(proSaved,HttpStatus.CREATED);
    }

    @PostMapping("/signup-property-owner")
    public ResponseEntity<?>createRegistrationPropertyOwner(@Valid @RequestBody UserDto userDto, BindingResult result) {
        if (result.hasErrors())
        {
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.NOT_ACCEPTABLE);
        }
        Optional<AppUser> opUsername = appUserRepository.findByUsername(userDto.getUsername());
        if (opUsername.isPresent()) {
            return new ResponseEntity<>("Username is already Registered", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Optional<AppUser> opEmail = appUserRepository.findByEmail(userDto.getEmail());
        if (opEmail.isPresent()) {
            return new ResponseEntity<>("Email is already Registered", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        userDto.setRole("ROLE_OWNER");
        RUserDto proSaved = appuserService.createProfile(userDto);
        return new ResponseEntity<>(proSaved,HttpStatus.CREATED);

    }

    @PostMapping("/login")
    public ResponseEntity<?>login(@RequestBody LoginDto loginDto){
        String login = appuserService.login(loginDto);
        if (login!=null)
        {
            TokenDto tokenDto=new TokenDto();
            tokenDto.setToken(login);
            tokenDto.setType("JWT");
            return new ResponseEntity<>(tokenDto,HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Username and Password not matched",HttpStatus.FORBIDDEN);
        }
    }
    @GetMapping("/message")
    public String getMessage(){
        return "hello";
    }

}
