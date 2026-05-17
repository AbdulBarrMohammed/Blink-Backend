package com.blink.blink_backend.controllers;

import com.blink.blink_backend.dto.BoardItemDto;
import com.blink.blink_backend.entities.BoardItem;
import com.blink.blink_backend.mappers.BoardItemMapper;
import com.blink.blink_backend.services.BoardItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "/board/{board_id}/board_items")
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

    @PostMapping("picture_id")
    public BoardItemDto createBoardItem(@PathVariable("board_id") UUID boardId,
                                        @PathVariable("picture_id") UUID pictureId,
                                        @RequestBody BoardItemDto boardItemDto) {
        // Create new Board item using board item service
        BoardItem createdBoardItem = boardItemService.createBoardItem(
                boardId,
                pictureId,
                boardItemMapper.fromDto(boardItemDto) // map board item dto to board item entity
        );

        return boardItemMapper.toDto(createdBoardItem); // map board item entity back to board item dto

    }

    @GetMapping(path = "/{board_item_id}")
    public Optional<BoardItemDto> getBoardItem(
            @PathVariable("board_id") UUID boardId,
            @PathVariable("board_item_id") UUID boardItemId
    ) {
        return boardItemService.getBoardItem(boardId, boardItemId).map(boardItemMapper::toDto);
    }

    @PutMapping(path = "/{board_item_id}")
    public BoardItemDto updateBoardItem(
            @PathVariable("board_id") UUID boardId,
            @PathVariable("board_item_id") UUID boardItemId,
            @RequestBody BoardItemDto boardItemDto
    ) {
        BoardItem updatedBoardItem = boardItemService.updateBoardItem(boardId, boardItemId, boardItemMapper.fromDto(boardItemDto));

        return boardItemMapper.toDto(updatedBoardItem);
    }

    @DeleteMapping(path = "/{board_item_id}")
    public void deleteBoardItem(
            @PathVariable("board_id") UUID boardId,
            @PathVariable("board_item_id") UUID boardItemId

    ) {
        boardItemService.deleteBoardItem(boardId, boardItemId);
    }
}
