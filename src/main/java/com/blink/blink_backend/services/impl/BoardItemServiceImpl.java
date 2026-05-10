package com.blink.blink_backend.services.impl;

import com.blink.blink_backend.entities.Board;
import com.blink.blink_backend.entities.BoardItem;
import com.blink.blink_backend.repositories.BoardItemRepository;
import com.blink.blink_backend.repositories.BoardRepository;
import com.blink.blink_backend.services.BoardItemService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class BoardItemServiceImpl implements BoardItemService {

    private final BoardItemRepository boardItemRepository;
    private final BoardRepository boardRepository;

    public BoardItemServiceImpl(BoardItemRepository boardItemRepository, BoardRepository boardRepository) {
        this.boardItemRepository = boardItemRepository;
        this.boardRepository = boardRepository;
    }

    @Override
    public List<BoardItem> listBoardItems(UUID boardId) {
        // find boardItems that match Board id
        return boardItemRepository.findByBoardId(boardId);
    }

    @Override
    public BoardItem createBoardItem(UUID boardId, BoardItem boardItem) {
        if (boardItem.getId() != null) {
            throw new IllegalArgumentException("Board Item already has an ID");
        }

        // Makes sure the board item has a value for both x and y double values
        if (boardItem.getX() == 0.0 || boardItem.getY() == 0.0) {
            throw new IllegalArgumentException("Board item must have x and y coordinates");
        }

        // Grab the board by the provided board id
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Board ID provided"));

        BoardItem boardItemToSave = new BoardItem(
                boardItem.getY(),
                boardItem.getId(),
                boardItem.getX(),
                board,
                null // This will be future picture object

        );

        return boardItemRepository.save(boardItemToSave);
    }

    @Override
    public Optional<BoardItem> getBoardItem(UUID boardId, UUID boardItemId) {
        return boardItemRepository.findByBoardIdAndId(boardId, boardItemId);
    }

    @Override
    public BoardItem updateBoardItem(UUID boardId, UUID boardItemId, BoardItem boardItem) {
        if (boardItem.getId() == null) {
            throw new IllegalArgumentException("Board item must have an ID");
        }

        if (!Objects.equals(boardItemId, boardItem.getId())) {
            throw new IllegalArgumentException("Board item IDs do not match");
        }

        if (boardItem.getX() == 0.0 || boardItem.getY() == 0.0) {
            throw new IllegalArgumentException("X and Y values must have values");
        }

        BoardItem existingBoardItem = boardItemRepository.findByBoardIdAndId(boardId, boardItemId).orElseThrow(() -> new IllegalArgumentException("Board item not found"));
        existingBoardItem.setX(boardItem.getX());
        existingBoardItem.setY(boardItem.getY());

        return boardItemRepository.save(existingBoardItem);

    }

    @Override
    public void deleteBoardItem(UUID boardId, UUID boardItemId) {
        boardItemRepository.deleteByBoardIdAndId(boardId, boardItemId);
    }

}
