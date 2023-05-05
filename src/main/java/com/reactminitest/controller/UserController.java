package com.reactminitest.controller;

import com.reactminitest.model.User;
import com.reactminitest.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("user")
public class UserController {
    @Autowired
    IUserService userService;
    @PostMapping("register")
    public ResponseEntity<String> register(User user){
        Optional<User> byName=userService.findByName(user.getUsername());
        if(byName.isPresent())return new ResponseEntity<>(HttpStatus.CONFLICT);
        userService.save(user);
        return ResponseEntity.ok("Registered");
    }
    @PostMapping("login")
    public ResponseEntity<String> login(User user){
        Optional<User> byName=userService.findByName(user.getUsername());
        if(byName.isEmpty())return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        if(byName.get().getPassword().equals(user.getPassword()))
            return ResponseEntity.ok("Logged in");
        else return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
}
