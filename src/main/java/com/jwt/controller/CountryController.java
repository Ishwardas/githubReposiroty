package com.jwt.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/country")
public class CountryController {

    ///api/v1/country/addCountry
    @PostMapping("/addCountry")
    public ResponseEntity<String>addCountry()
    {
        return  new ResponseEntity<>("Country added", HttpStatus.CREATED);
    }
}
