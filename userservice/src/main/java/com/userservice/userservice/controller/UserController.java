package com.userservice.userservice.controller;

import com.userservice.userservice.model.Users;
import com.userservice.userservice.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(path = "add/user", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Users addProvider(@RequestBody Users user){
        return userService.saveUser(user);
    }

    @GetMapping("get/users")
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMINTRAINEE')")
    public List<Users> getProviders(){
        return userService.getUsers();
    }

    @GetMapping("get/user/{id}")
    public Users getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }
}
