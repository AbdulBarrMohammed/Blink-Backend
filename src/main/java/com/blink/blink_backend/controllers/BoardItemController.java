package com.blink.blink_backend.controllers;

import com.blink.blink_backend.dto.BoardItemDto;
import com.blink.blink_backend.entities.BoardItem;
import com.blink.blink_backend.mappers.BoardItemMapper;
import com.blink.blink_backend.services.BoardItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/board/{board_id}/board_item")
public class BoardItemController {

    private final BoardItemService boardItemService;
    private final BoardItemMapper boardItemMapper;

    public BoardItemController(BoardItemService boardItemService, BoardItemMapper boardItemMapper) {
        this.boardItemService = boardItemService;
        this.boardItemMapper = boardItemMapper;
    }

    @GetMapping
    public List<BoardItemDto> listBoardItems(@PathVariable("board_id") UUID boardId) {
        return boardItemService.listBoardItems(boardId)
                .stream()
                .map(boardItemMapper::toDto)
                .toList();
    }

    @PostMapping
    public BoardItemDto createBoardItem(@PathVariable("board_id") UUID boardId,
                                        @RequestBody BoardItemDto boardItemDto) {
        // Create new Board item using board item service
        BoardItem createdBoardItem = boardItemService.createBoardItem(
                boardId,
                boardItemMapper.fromDto(boardItemDto) // map board item dto to board item entity
        );

        return boardItemMapper.toDto(createdBoardItem); // map board item entity back to board item dto

    }
}
