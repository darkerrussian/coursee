package com.example.coursee;


import com.example.coursee.entities.Role;
import com.example.coursee.entities.RoleRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService {

    private RoleRepo roleRepo;

    @Autowired
    public void setRoleRepo(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    public Role getUserRole(){return  roleRepo.findByName("ROLE_USER").get();}
}
