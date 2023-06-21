package com.bridgeLabz.userFitnessGoals.controller;

import com.bridgeLabz.userFitnessGoals.dto.FitnessGoalDto;
import com.bridgeLabz.userFitnessGoals.dto.ResponseDto;
import com.bridgeLabz.userFitnessGoals.service.FitnessGoalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fitnessGoal")
public class FitnessGoalController {
    @Autowired
    FitnessGoalService service;

    @PostMapping("/add")
    public ResponseEntity<ResponseDto> addGoal(@Valid @RequestBody FitnessGoalDto dto, @RequestHeader String email, @RequestHeader String password) {
        ResponseDto responseDto = new ResponseDto("Goal Details ", service.addFitnessGoal(email, password, dto));
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/getById")
    public ResponseEntity<ResponseDto> findById(@RequestHeader String token) {
        ResponseDto dto = new ResponseDto("Fitness details", service.getById(token));
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<ResponseDto> findAll() {
        ResponseDto dto = new ResponseDto("All Task details", service.getAll());
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }


    @PutMapping("/update")
    public ResponseEntity<ResponseDto> update(@RequestHeader String token, @Valid @RequestBody FitnessGoalDto dto) {
        ResponseDto responseDto = new ResponseDto("Update fitness details", service.update(token, dto));
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PutMapping("/updateByProgress")
    public ResponseEntity<ResponseDto> updateProgress(@RequestHeader String token) {
        ResponseDto dto = new ResponseDto("Updating progress", service.updateProgress(token));
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> delete(@RequestHeader String token) {
        ResponseDto dto = new ResponseDto("Goal details are deleted", service.delete(token));
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/sortByStartDate")
    public ResponseEntity<ResponseDto> sortByStartDate() {
        ResponseDto dto = new ResponseDto("Sort by start date", service.sortByStartDate());
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/sortByEndDate")
    public ResponseEntity<ResponseDto> sortByEndDate() {
        ResponseDto dto = new ResponseDto("Sort By End Date", service.sortByEndDate());
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
