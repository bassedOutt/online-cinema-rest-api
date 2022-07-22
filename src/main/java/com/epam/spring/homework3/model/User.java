package com.epam.spring.homework3.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.EAGER;

@Data
@Entity
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String surname;

    @Column(unique = true)
    private String email;
    private String password;

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private List<Ticket> tickets;

    @ManyToMany(fetch = EAGER)
    private List<Role> roles = new ArrayList<>();

    public void addRole(Role role) {
        roles.add(role);
    }
}
