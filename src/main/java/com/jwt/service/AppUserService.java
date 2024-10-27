package com.jwt.service;

import com.jwt.entity.AppUser;
import com.jwt.payload.LoginDto;
import com.jwt.payload.RUserDto;
import com.jwt.payload.UserDto;
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


    public RUserDto createProfile(UserDto userDto) {
        AppUser user = modelMapper.map(userDto, AppUser.class);
        String hashpw = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(5));
        user.setPassword(hashpw);
        AppUser save = appUserRepository.save(user);
        return modelMapper.map(save, RUserDto.class);

    }

    public String login(LoginDto loginDto) {
        Optional<AppUser> username =
                appUserRepository.findByUsername(loginDto.getUsername());
        if (username.isPresent())
        {
            boolean hashpw = BCrypt.checkpw(loginDto.getPassword(), username.get().getPassword());
            if (hashpw){
                String token = jwtService.generateToken(loginDto.getUsername());
                return token;
            }
        }
        return null;
    }
}
