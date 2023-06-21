package com.bridgeLabz.userFitnessGoals.model;

import com.bridgeLabz.userFitnessGoals.dto.FitnessGoalDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "FitnessGoal")
@Data
@NoArgsConstructor
public class FitnessGoal {
    @Id
    @GeneratedValue
    int id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private int progress;

    public FitnessGoal(FitnessGoalDto dto) {
        description = dto.getDescription();
        startDate = dto.getStartDate();
        endDate = dto.getEndDate();
    }

}
