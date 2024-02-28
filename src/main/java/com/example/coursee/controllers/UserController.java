package com.example.coursee.controllers;

import com.example.coursee.entities.Role;
import com.example.coursee.entities.RoleRepo;
import com.example.coursee.entities.User;
import com.example.coursee.entities.UserRepo;
import com.example.coursee.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/usercontroller")
public class UserController {


    private UserRepo userRepo;


    private RoleRepo roleRepo;

    @Autowired
    public void setUserRepo(UserRepo userRepo) {
        this.userRepo = userRepo;
    }
    @Autowired
    public void setRoleRepo(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    @PostMapping("/post")
    public User postUser (@RequestBody User user){

        return userRepo.save(user);
    }

    /*@GetMapping("/read")
    public List<User> readAllUsers (User user){
       return userRepo.findAll();



    }*/

    @GetMapping("/read/{id}")
    public User getUserById (@PathVariable(value = "id") Integer id){
        return userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));



    }

    @GetMapping("/userinfo")
    public String userAccess(Principal principal){
        if(principal == null)
            return null;
        return principal.getName();
    }


   /* @PutMapping("/postrole/{user_id}/{role_id}")
    User userWithRole(@PathVariable(value = "user_id") Integer user_id, @PathVariable(value = "role_id") Integer role_id){

        User user = userRepo.getOne(user_id);
        Role role = roleRepo.getOne(role_id);
        user.enrollUser(role);
        return userRepo.save(user);

    }*/
}
