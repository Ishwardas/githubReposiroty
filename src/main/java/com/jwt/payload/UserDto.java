package com.jwt.payload;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class UserDto {
    @NotEmpty(message = "Enter name: ")
    @NotBlank(message = "Enter name:")
    @NotNull(message = "Enter name: ")
    @Size(min = 2,max = 100,message = "Enter Valid Name")
    private String name;
    @NotEmpty(message = "Enter username: ")
    @NotBlank(message = "Enter username:")
    @NotNull(message = "Enter username: ")
    @Size(min = 2,max = 100,message = "Enter Valid username")
    private String username;
    @NotEmpty(message = "Enter email: ")
    @NotBlank(message = "Enter email:")
    @NotNull(message = "Enter email: ")
    @Size(min = 3,max = 100,message = "Enter Valid email")
    @Email(message = "Enter Valid Email")
    private String email;
    @NotEmpty(message = "Enter password: ")
    @NotBlank(message = "Enter password:")
    @NotNull(message = "Enter password: ")
    @Size(min = 3,max = 100,message = "Enter Valid password")
    private String password;

    private String role;
}
