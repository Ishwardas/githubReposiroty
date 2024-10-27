package com.jwt.service;

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

}
