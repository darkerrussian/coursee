package com.example.coursee.entities;


import com.example.coursee.entities.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@Table(name = "users1")


public class User extends AbstractPersistable<Integer> {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "username")
    private String username;
    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    /*@Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)*/

    @ManyToMany
    @JoinTable(
            name = "user_roles1",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))

    private Collection<Role> roles;
  /*  private List<Role> roles = new ArrayList<>();

    public void enrollUser(Role role) {
        roles.add(role);
    }*/
}
