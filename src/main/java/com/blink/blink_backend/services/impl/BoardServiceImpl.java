package com.blink.blink_backend.services.impl;

import com.blink.blink_backend.entities.Board;
import com.blink.blink_backend.entities.User;
import com.blink.blink_backend.repositories.BoardRepository;
import com.blink.blink_backend.repositories.UserRepository;
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
    private final UserRepository userRepository;

    public BoardServiceImpl(BoardRepository boardRepository, UserRepository userRepository) {
        this.boardRepository = boardRepository;
        this.userRepository = userRepository;
    }
    @Override
    public List<Board> listBoards(UUID userId) {
        return boardRepository.findByUserId(userId);
    }

    @Override
    public Board createBoard(UUID userId, Board board) {

        if (userId == null) {
            throw new IllegalArgumentException("User id must not be null");
        }
        // Checks if the board passed ID already has an ID
        if (board.getId() != null) {
            throw new IllegalArgumentException("Board already has this ID");
        }

        // Checks to make sure any board being created has a title
        if (board.getTitle() == null || board.getTitle().isBlank()) {
            throw new IllegalArgumentException("Board title must be present");
        }

        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Invalid User ID provided"));;
        LocalDateTime now = LocalDateTime.now();
        return boardRepository.save(
                new Board(

                        now,
                        board.getId(),
                        board.getTitle(),
                        null, // would be null because user did not create any board items yet ?
                        user



                )
        );

    }

    @Override
    public Optional<Board> getBoard(UUID userId ,UUID id) {
        return boardRepository.findByUserIdAndId(userId, id);
    }

    @Override
    public Board updateBoard(UUID userId, UUID boardId, Board board) {
        if (userId == null) {
            throw new IllegalArgumentException("User must have an ID");
        }
        if (board.getId() == null) {
            throw new IllegalArgumentException("Board must have an ID");
        }

        // Checks to see if the ID in url is the same as the ID in JSON body
        if (!Objects.equals(board.getId(), boardId)) {
            throw new IllegalArgumentException("Attempting to change board ID, this is not allowed");
        }

        Board existingBoard = boardRepository.findByUserIdAndId(userId, boardId).orElseThrow(() -> new IllegalArgumentException("Board not found"));

        //Updating the title of the board
        existingBoard.setTitle(board.getTitle());
        return boardRepository.save(existingBoard);
    }

    @Override
    public void deleteBoard(UUID userId, UUID boardId) {
        boardRepository.deleteByUserIdAndId(userId, boardId);
    }
}
