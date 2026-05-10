package com.blink.blink_backend.services;

import com.blink.blink_backend.entities.BoardItem;

import java.util.List;
import java.util.UUID;

public interface BoardItemService {

    // Gets id of board that boarditems belong too
    List<BoardItem> listBoardItems(UUID boardId);
    BoardItem createBoardItem(UUID boardId, BoardItem boardItem);

    //BoardItem updateBoardItem(UUID boardItemId, BoardItem boardItem);
}
