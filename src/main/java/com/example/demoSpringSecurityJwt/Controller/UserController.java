package com.example.demoSpringSecurityJwt.Controller;

import com.example.demoSpringSecurityJwt.Entity.User;
import com.example.demoSpringSecurityJwt.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostConstruct
    public void initRoleAndUser() {
        userService.initRoleAndUser();
    }

    @PostMapping({"/registerNewUser"})
    @PreAuthorize("hasRole('Admin')") //give permission to Admin or User
    public User registerNewUser(@RequestBody User user) {
        return userService.registerNewUser(user);
    }

    @GetMapping({"/forAdmin"})
    @PreAuthorize("hasRole('Admin')")
    public String forAdmin(){
        return "This URL is only accessible to the admin Thank You.";
    }

    @GetMapping({"/forUser"})
    @PreAuthorize("hasRole('User')")
    public String forUser(){
        return "This URL is only accessible to the user Thank You";
    }

    @GetMapping({"/showDataBase"}) //for all data shows by Admin only
    @PreAuthorize("hasRole('Admin')")
    public Iterable<User> showDataBase() {
        return userService.showDataBase();
    }


    @PreAuthorize("hasRole('Admin')")
    @GetMapping("/showSpecificData/{userFirstName}") //for specific data shows by Admin only
    public ResponseEntity<User> showSpecificData(@PathVariable("userFirstName") String userFirstName) {
        User user= userService.showSpecificData(userFirstName);
        if(user.getUserFirstName().equals(userFirstName)){
            return new ResponseEntity<>(user, HttpStatus.FOUND);
        }
        else{
            return new ResponseEntity<>(user,HttpStatus.BAD_REQUEST);
        }
    }
}

