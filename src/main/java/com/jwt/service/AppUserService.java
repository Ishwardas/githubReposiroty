package com.jwt.service;

import com.jwt.entity.AppUser;
import com.jwt.repository.AppUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class AppUserService {
    private AppUserRepository appUserRepository;
   private ModelMapper modelMapper;
    public AppUserService(AppUserRepository appUserRepository, ModelMapper modelMapper) {
        this.appUserRepository = appUserRepository;
        this.modelMapper = modelMapper;
    }


    public AppUser createProfile(AppUser user) {
        AppUser save = appUserRepository.save(user);
        return save;

    }
}
