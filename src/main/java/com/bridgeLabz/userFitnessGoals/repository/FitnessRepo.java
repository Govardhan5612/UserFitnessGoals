package com.bridgeLabz.userFitnessGoals.repository;

import com.bridgeLabz.userFitnessGoals.model.FitnessGoal;
import com.bridgeLabz.userFitnessGoals.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface FitnessRepo extends JpaRepository<FitnessGoal,Integer> {
    @Query(value = "SELECT * FROM fitness_goal ORDER BY end_date ASC;", nativeQuery = true)
    List<FitnessGoal> sortByEndDate();
    @Query(value = "SELECT * FROM fitness_goal ORDER BY start_date ASC;", nativeQuery = true)
    List<FitnessGoal> sortByStartDate();
    @Query(value = "select * from fitness_goal where user_id= :userId", nativeQuery = true)
    List<FitnessGoal> getUserTasks(int userId);

}
