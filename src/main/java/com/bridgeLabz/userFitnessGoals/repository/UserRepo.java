package com.bridgeLabz.userFitnessGoals.repository;

import com.bridgeLabz.userFitnessGoals.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Integer> {
    @Query(value = "select * from user where email = :email", nativeQuery = true)
    Optional<User> findEmail(String email);
    @Query(value = "select * from user where email =:email AND password =:password", nativeQuery = true)
    Optional<User> loginUser(String email, String password);
}
