package com.bridgeLabz.userFitnessGoals.service;

import com.bridgeLabz.userFitnessGoals.dto.UserDto;
import com.bridgeLabz.userFitnessGoals.exception.FitnessException;
import com.bridgeLabz.userFitnessGoals.exception.UserException;
import com.bridgeLabz.userFitnessGoals.model.FitnessGoal;
import com.bridgeLabz.userFitnessGoals.model.User;
import com.bridgeLabz.userFitnessGoals.repository.FitnessRepo;
import com.bridgeLabz.userFitnessGoals.repository.UserRepo;
import com.bridgeLabz.userFitnessGoals.util.JWT_Token;
import jakarta.servlet.http.HttpServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;
    @Autowired
    JWT_Token token;
    @Autowired
    FitnessRepo fitnessRepo;
    @Autowired
    FitnessGoalService fitnessGoalService;

    public String addUser(UserDto dto) {
        Optional<User> s = userRepo.findEmail(dto.getEmail());
        if (s.isEmpty()) {
            User person = new User(dto);
            userRepo.save(person);
            return token.createToken(person.getUser_id());
        } else {
            return "This email already present in database";
        }
    }

    public User findByUserId(String userToken) {
        int id = token.decode(userToken);
        return userRepo.findById(id).orElseThrow(() -> new UserException(id + " not found in database"));
    }

    public List<User> findAllUsers() {
        return userRepo.findAll();
    }

    public User updateUser(String userToken, UserDto dto) {
        int id = token.decode(userToken);
        Optional<User> user = userRepo.findById(id);
        if (user.isEmpty()) {
            return userRepo.findById(id).orElseThrow(() -> new UserException(id + " not found in database"));
        } else {
            user.get().setName(dto.getName());
            user.get().setEmail(dto.getEmail());
            user.get().setPassword(dto.getPassword());
            return userRepo.save(user.get());
        }
    }

    public String deleteUser(String userToken) {
        int id = token.decode(userToken);
        User user = findByUserId(userToken);
        List<FitnessGoal> list = fitnessRepo.getUserTasks(user.getUser_id());
        list.stream().forEach(x -> fitnessRepo.deleteById(x.getId()));
        userRepo.deleteById(id);
        return id + " All details are deleted";
    }
    public List<FitnessGoal> userTasks(String userToken) {
        int id = token.decode(userToken);
        User user = findByUserId(userToken);
        List<FitnessGoal> list = fitnessRepo.getUserTasks(user.getUser_id());
        List<FitnessGoal>  goalsList =list.stream().map(x -> fitnessRepo.findById(x.getId()).orElseThrow(()->new FitnessException(id+" not found"))).toList();
        return goalsList;
    }
}
