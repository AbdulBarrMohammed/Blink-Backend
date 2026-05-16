package com.blink.blink_backend.services;

import com.blink.blink_backend.entities.Board;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BoardService {
    List<Board> listBoard();
    Board createBoard(UUID userId, Board board);
    Optional<Board> getBoard(UUID userId, UUID id);
    Board updateBoard(UUID userId, UUID boardId, Board board);
    void deleteBoard(UUID userId, UUID boardId);
}
