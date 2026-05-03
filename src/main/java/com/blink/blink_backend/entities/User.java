package com.blink.blink_backend.entities;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "email", updatable = false, nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToMany(mappedBy = "User", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private List<Board> boards;

    @OneToMany(mappedBy = "User", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private List<Picture> pictures;


}
