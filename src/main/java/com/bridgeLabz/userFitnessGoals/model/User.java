package com.bridgeLabz.userFitnessGoals.model;

import com.bridgeLabz.userFitnessGoals.dto.UserDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "User")
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue
    int User_id;
    private String name;
    private String email;
    private String password;

    public User(UserDto dto) {
        name = dto.getName();
        email = dto.getEmail();
        password = dto.getPassword();
    }
}
