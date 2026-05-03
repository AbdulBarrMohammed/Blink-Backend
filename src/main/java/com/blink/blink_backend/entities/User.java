package com.blink.blink_backend.entities;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;
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

    public User() {
    }

    public User(List<Picture> pictures, List<Board> boards, String password, String email, UUID id) {
        this.pictures = pictures;
        this.boards = boards;
        this.password = password;
        this.email = email;
        this.id = id;
    }

    public List<Board> getBoards() {
        return boards;
    }

    public void setBoards(List<Board> boards) {
        this.boards = boards;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(boards, user.boards) && Objects.equals(pictures, user.pictures);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password, boards, pictures);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", boards=" + boards +
                ", pictures=" + pictures +
                '}';
    }
}
