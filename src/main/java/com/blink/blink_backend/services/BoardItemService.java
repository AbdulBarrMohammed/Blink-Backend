package com.blink.blink_backend.services;

import com.blink.blink_backend.entities.BoardItem;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BoardItemService {

    // Gets id of board that boarditems belong too
    List<BoardItem> listBoardItems(UUID boardId);
    BoardItem createBoardItem(UUID boardId, UUID pictureId, BoardItem boardItem);
    Optional<BoardItem> getBoardItem(UUID boardId, UUID boardItemId); // returning Optional because the board item might not exist

    BoardItem updateBoardItem(UUID boardId, UUID boardItemId, BoardItem boardItem);
    void deleteBoardItem(UUID boardId, UUID boardItemId);
}
