package com.blink.blink_backend.services.impl;

import com.blink.blink_backend.entities.Board;
import com.blink.blink_backend.repositories.BoardRepository;
import com.blink.blink_backend.services.BoardService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

// This is the service, it is the brain of the backend that decides what should happen
// and coordinates the work between the controller and repository.
@Service
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    public BoardServiceImpl(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }
    @Override
    public List<Board> listBoard() {
        return boardRepository.findAll();
    }

    @Override
    public Board createBoard(Board board) {
        // Checks if the board passed ID already has an ID
        if (board.getId() != null) {
            throw new IllegalArgumentException("Board already has this ID");
        }

        // Checks to make sure any board being created has a title
        if (board.getTitle() == null || board.getTitle().isBlank()) {
            throw new IllegalArgumentException("Board title must be present");
        }

        LocalDateTime now = LocalDateTime.now();
        return boardRepository.save(
                new Board(

                        now,
                        null,
                        board.getTitle(),
                        null,
                        null



                )
        );

    }

    @Override
    public Optional<Board> getBoard(UUID id) {
        return boardRepository.findById(id);
    }

    @Override
    public Board updateBoard(UUID boardId, Board board) {
        if (board.getId() == null) {
            throw new IllegalArgumentException("Board must have an id");
        }

        // Checks to see if the ID in url is the same as the ID in JSON body
        if (!Objects.equals(board.getId(), boardId)) {
            throw new IllegalArgumentException("Attempting to change board ID, this is not allowed");
        }

        Board existingBoard = boardRepository.findById(boardId).orElseThrow(() -> new IllegalArgumentException("Board not found"));

        //Updating the title of the board
        existingBoard.setTitle(board.getTitle());
        return boardRepository.save(existingBoard);
    }

    @Override
    public void deleteBoard(UUID boardId) {
        boardRepository.deleteById(boardId);
    }
}
