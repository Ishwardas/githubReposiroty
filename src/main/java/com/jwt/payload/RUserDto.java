package com.jwt.payload;

import lombok.Data;

@Data
public class RUserDto {
    private String name;
    private String username;
    private String email;
    private String role;

}