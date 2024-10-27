package com.jwt.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginDto {
    @NotEmpty(message = "Enter username: ")
    @NotBlank(message = "Enter username:")
    @NotNull(message = "Enter username: ")
    @Size(min = 3,max = 100,message = "Enter Valid username")
    private String username;
    @NotEmpty(message = "Enter password: ")
    @NotBlank(message = "Enter password:")
    @NotNull(message = "Enter password: ")
    @Size(min = 3,max = 100,message = "Enter Valid password")
    private String password;

}
