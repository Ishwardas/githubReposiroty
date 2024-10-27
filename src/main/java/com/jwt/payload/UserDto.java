package com.jwt.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @Size(min = 3,max = 100,message = "Enter Valid Name")
    private String name;
    private String username;
    private String email;
    private String password;
    private String role;
}
