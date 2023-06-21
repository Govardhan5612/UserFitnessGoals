package com.bridgeLabz.userFitnessGoals.dto;

import com.bridgeLabz.userFitnessGoals.model.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDate;

@Data
public class FitnessGoalDto {
    @Pattern(regexp = "^[a-zA-Z0-9 ]{1,50}$", message = "Enter minimum three characters")
    private String description;
    @JsonFormat(pattern = "dd MM yyyy")
    @PastOrPresent(message = "Enter past date")
    private LocalDate startDate;
    @JsonFormat(pattern = "dd MM yyyy")
    @FutureOrPresent(message = "Enter future date")
    private LocalDate endDate;
    private int progress;


}
