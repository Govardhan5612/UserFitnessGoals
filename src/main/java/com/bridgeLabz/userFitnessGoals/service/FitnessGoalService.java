package com.bridgeLabz.userFitnessGoals.service;

import com.bridgeLabz.userFitnessGoals.dto.FitnessGoalDto;
import com.bridgeLabz.userFitnessGoals.dto.UserDto;
import com.bridgeLabz.userFitnessGoals.exception.FitnessException;
import com.bridgeLabz.userFitnessGoals.model.FitnessGoal;
import com.bridgeLabz.userFitnessGoals.model.User;
import com.bridgeLabz.userFitnessGoals.repository.FitnessRepo;
import com.bridgeLabz.userFitnessGoals.repository.UserRepo;
import com.bridgeLabz.userFitnessGoals.util.JWT_Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;
import java.util.Optional;

@Service
public class FitnessGoalService {
    @Autowired
    UserRepo userRepo;
    @Autowired
    JWT_Token token;
    @Autowired
    FitnessRepo fitnessRepo;

    public String addFitnessGoal(String email, String password, FitnessGoalDto dto) {
        Optional<User> user = userRepo.loginUser(email, password);
        if (user.isEmpty()) {
            return "This email details not found in database";
        } else {
            dto.setProgress(0);
            FitnessGoal goal = new FitnessGoal(dto);
            goal.setUser(user.get());
            fitnessRepo.save(goal);
            return token.createToken(goal.getId());
        }
    }

    public List<FitnessGoal> getAll() {
        return fitnessRepo.findAll();
    }

    public FitnessGoal getById(String tokenValue) {
        int id = token.decode(tokenValue);
        return fitnessRepo.findById(id).orElseThrow(() -> new FitnessException(id + " id not present"));
    }

    public FitnessGoal update(String tokenValue, FitnessGoalDto dto) {
        int id = token.decode(tokenValue);
        Optional<FitnessGoal> list = fitnessRepo.findById(id);
        if (list.isEmpty()) {
            return fitnessRepo.findById(id).orElseThrow(() -> new FitnessException(id + " id not present"));
        } else {
            list.get().setDescription(dto.getDescription());
            list.get().setStartDate(dto.getStartDate());
            list.get().setEndDate(dto.getEndDate());
            return fitnessRepo.save(list.get());
        }
    }

    public FitnessGoal updateProgress(String tokenValue) {
        int id = token.decode(tokenValue);
        Optional<FitnessGoal> list = fitnessRepo.findById(id);
        if (list.isEmpty()) {
            return fitnessRepo.findById(id).orElseThrow(() -> new FitnessException(id + " id not present"));
        } else {
            if (list.get().getProgress() < 100) {
                int actual = list.get().getProgress();
                list.get().setProgress(actual + 2);
                return fitnessRepo.save(list.get());
            } else {
                return list.get();
            }
        }
    }

    public String delete(String tokenValue) {
        int id = token.decode(tokenValue);
        fitnessRepo.deleteById(id);
        return id + " are deleted";
    }

    public List<FitnessGoal> sortByStartDate(){
        return fitnessRepo.sortByStartDate();
    }
    public List<FitnessGoal> sortByEndDate(){
        return fitnessRepo.sortByEndDate();
    }
}
