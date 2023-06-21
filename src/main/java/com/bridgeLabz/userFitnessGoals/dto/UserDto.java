package com.bridgeLabz.userFitnessGoals.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserDto {
    @Pattern(regexp = "[A-Z]{1}[a-z]{2,}[\\s]{0,1}[A-Z]{0,1}[a-z]{0,}[\\s]{0,1}[A-Z]{0,1}[a-z]{0,}", message = "Invalid name")
    private String name;
    @Email(regexp = "[a-zA-Z0-9]{6,25}@gmail.com", message = "Invalid email address")
    private String email;
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%&*]).{8,20}$", message = "Enter Strong password")
    private String password;
}
