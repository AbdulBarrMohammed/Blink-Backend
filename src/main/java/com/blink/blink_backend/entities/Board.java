package com.blink.blink_backend.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "Boards")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "Board", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private List<BoardItem> boardItems;

    // Many boards belonging to one user
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Board() {
    }

    public Board(LocalDateTime createdAt, UUID id, String title, List<BoardItem> boardItems, User user) {
        this.createdAt = createdAt;
        this.id = id;
        this.title = title;
        this.boardItems = boardItems;
        this.user = user;
    }

    public List<BoardItem> getBoardItems() {
        return boardItems;
    }

    public void setBoardItems(List<BoardItem> boardItems) {
        this.boardItems = boardItems;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Board board = (Board) o;
        return Objects.equals(id, board.id) && Objects.equals(title, board.title) && Objects.equals(createdAt, board.createdAt) && Objects.equals(boardItems, board.boardItems) && Objects.equals(user, board.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, createdAt, boardItems, user);
    }

    @Override
    public String toString() {
        return "Board{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", createdAt=" + createdAt +
                ", boardItems=" + boardItems +
                ", user=" + user +
                '}';
    }
}
