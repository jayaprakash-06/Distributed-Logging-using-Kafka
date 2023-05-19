package com.userservice.userservice.service;

import com.userservice.userservice.model.Users;
import com.userservice.userservice.repo.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepo userRepo;
    private Logger logger = LoggerFactory.getLogger(UserService.class);

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public List<Users> getUsers() {
        List<Users> users;
        users = userRepo.findAll();
        logger.info("All users details fetched Successfully"+users);
//        for(Users user : users ){
//            logger.info(user.toString());
//        }
        return users;
    }

    public Users saveUser(Users user) {
        ResponseEntity responseEntity;
        Users savedUser = userRepo.save(user);
        logger.info("New User Added:"+savedUser);
        return savedUser;
    }

    public Users getUserById(Long id) {
        Users user = userRepo.findById(id).get();
        logger.info("User with Id "+id+" fetched successfully:"+user);
        return user;
    }
}
