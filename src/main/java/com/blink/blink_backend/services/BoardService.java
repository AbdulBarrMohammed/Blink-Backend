package com.blink.blink_backend.services;

import com.blink.blink_backend.entities.Board;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BoardService {
    List<Board> listBoard();
    Board createBoard(Board board);
    Optional<Board> getBoard(UUID id);
    Board updateBoard(UUID boardId, Board board);
    void deleteBoard(UUID boardId);
}
