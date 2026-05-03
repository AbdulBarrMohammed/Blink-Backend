package com.blink.blink_backend.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "BoardItems")
public class BoardItem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "x", updatable = false, nullable = false)
    private double x;

    @Column(name = "y", updatable = false, nullable = false)
    private double y;


    // Many board items belong to one board
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;


    //Many board items belonging to one picture
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "picture_id")
    private Picture picture;


    public BoardItem() {
    }

    public BoardItem(double y, UUID id, double x, Board board, Picture picture) {
        this.y = y;
        this.id = id;
        this.x = x;
        this.board = board;
        this.picture = picture;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        BoardItem boardItem = (BoardItem) o;
        return Double.compare(x, boardItem.x) == 0 && Double.compare(y, boardItem.y) == 0 && Objects.equals(id, boardItem.id) && Objects.equals(board, boardItem.board) && Objects.equals(picture, boardItem.picture);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, x, y, board, picture);
    }

    @Override
    public String toString() {
        return "BoardItem{" +
                "id=" + id +
                ", x=" + x +
                ", y=" + y +
                ", board=" + board +
                ", picture=" + picture +
                '}';
    }
}
