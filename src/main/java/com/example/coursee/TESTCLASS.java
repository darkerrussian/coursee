package com.example.coursee;

import com.example.coursee.entities.User;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

@NoArgsConstructor

public class TESTCLASS {

    private String name;
    private String email;
    private List<User> list;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TESTCLASS testclass = (TESTCLASS) o;
        return name.equals(testclass.name) && email.equals(testclass.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email);
    }

    TESTCLASS (String name, String email, List<User> list){
        this.name = name;
        this.email = email;
        this.list = list;

    }

}
