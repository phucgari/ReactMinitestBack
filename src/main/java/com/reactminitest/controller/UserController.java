package com.reactminitest.controller;

import com.reactminitest.model.Role;
import com.reactminitest.model.User;
import com.reactminitest.service.IRoleService;
import com.reactminitest.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("user")
public class UserController {
    @Autowired
    IUserService userService;
    @Autowired
    IRoleService roleService;
    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody User user){
        Optional<User> byName=userService.findByName(user.getUsername());
        if(byName.isPresent())return new ResponseEntity<>(HttpStatus.CONFLICT);
        Role role = roleService.getById(user.getRole().getId()).get();
        user.setRole(role);
        userService.save(user);
        return ResponseEntity.ok(role.getName().toString());
    }
    @PostMapping("login")
    public ResponseEntity<User> login(@RequestBody User user){
        Optional<User> byName=userService.findByName(user.getUsername());
        if(byName.isEmpty())return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        if(byName.get().getPassword().equals(user.getPassword()))
            return ResponseEntity.ok(byName.get());
        else return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
    @GetMapping("roles")
    public ResponseEntity<List<Role>> getRoles(){
        return ResponseEntity.ok(roleService.getAll());
    }
    @GetMapping("check/{username}")
    public ResponseEntity<String> checkUserName(@PathVariable String username){
        Optional<User> user=userService.findByName(username);
        if(user.isEmpty())return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
}
