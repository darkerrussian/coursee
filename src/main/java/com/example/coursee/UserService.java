package com.example.coursee;

import com.example.coursee.dto.RegistrUserDto;

import com.example.coursee.entities.User;
import com.example.coursee.entities.UserRepo;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class UserService implements UserDetailsService {


    private UserRepo userRepo;
    private RoleService roleService;
    private PasswordEncoder passwordEncoder;


    @Autowired
    public void setUserRepo(UserRepo userRepo) {
        this.userRepo = userRepo;
    }
    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }
    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<User> findByUsername(String username){
        return userRepo.findUserByUsername(username);
    }


    /*@Autowired
    public void setUserRepo(UserRepo userRepo) {
        this.userRepo = userRepo;
    }
*/


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found", username)));
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList())
        );


    }
    public User createNewUser(RegistrUserDto registrUserDto){
        User user = new User();
        user.setUsername(registrUserDto.getUsername());
        user.setEmail(registrUserDto.getEmail());
        user.setPassword(passwordEncoder.encode(registrUserDto.getPassword()));
        user.setRoles(List.of(roleService.getUserRole()));
        return userRepo.save(user);
    }
}
