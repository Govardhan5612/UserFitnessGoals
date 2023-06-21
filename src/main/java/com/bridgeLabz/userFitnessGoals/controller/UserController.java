package com.bridgeLabz.userFitnessGoals.controller;

import com.bridgeLabz.userFitnessGoals.dto.ResponseDto;
import com.bridgeLabz.userFitnessGoals.dto.UserDto;
import com.bridgeLabz.userFitnessGoals.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping("/add")
    public ResponseEntity<ResponseDto> addUser(@Valid@RequestBody UserDto dto){
        ResponseDto responseDto = new ResponseDto("Add the user details ",userService.addUser(dto));
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
    @GetMapping("/getById")
    public ResponseEntity<ResponseDto> getById(@RequestHeader String token){
        ResponseDto dto = new ResponseDto("User details : ",userService.findByUserId(token));
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }
    @GetMapping("/getAll")
    public ResponseEntity<ResponseDto> getUsers(){
        ResponseDto dto = new ResponseDto("All user details",userService.findAllUsers());
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> update(@Valid@RequestBody UserDto dto,@RequestHeader String token){
        ResponseDto responseDto = new ResponseDto("Update user details",userService.updateUser(token,dto));
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> delete(@RequestHeader String token){
        ResponseDto dto = new ResponseDto("Delete User details",userService.deleteUser(token));
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }
    @GetMapping("/getGoals")
    public ResponseEntity<ResponseDto> userGoals(@RequestHeader String token){
        ResponseDto dto = new ResponseDto("user All Goals",userService.userTasks(token));
        return  new ResponseEntity<>(dto,HttpStatus.OK);
    }




}
