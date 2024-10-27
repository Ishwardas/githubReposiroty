package com.jwt.service;

import com.jwt.entity.AppUser;
import com.jwt.payload.LoginDto;
import com.jwt.repository.AppUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserService {
    private AppUserRepository appUserRepository;
   private ModelMapper modelMapper;
   private JWTService jwtService;
    public AppUserService(AppUserRepository appUserRepository, ModelMapper modelMapper, JWTService jwtService) {
        this.appUserRepository = appUserRepository;
        this.modelMapper = modelMapper;
        this.jwtService = jwtService;
    }


    public AppUser createProfile(AppUser user) {
        AppUser save = appUserRepository.save(user);
        return save;

    }

    public String verifyLogin(LoginDto dto) {
        Optional<AppUser> opUsername = appUserRepository.findByUsername(dto.getUsername());
        if(opUsername.isPresent()) {
            AppUser appUser = opUsername.get();
            if (BCrypt.checkpw(dto.getPassword(), appUser.getPassword())) {
                String token = jwtService.generateToken(appUser.getUsername());
                return token;

            }
        }else {
            return null;
        }
       return null;
    }
}
