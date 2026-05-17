package com.blink.blink_backend.controllers;

import com.blink.blink_backend.dto.BoardDto;
import com.blink.blink_backend.entities.Board;
import com.blink.blink_backend.mappers.BoardMapper;
import com.blink.blink_backend.services.BoardService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

// This is the controller that handles incoming HTTP requests and communicates directly to the client
@RestController
@RequestMapping(path = "user/{user_id}/boards")
public class BoardController {

    private final BoardService boardService;
    private final BoardMapper boardMapper;

    public BoardController(BoardService boardService, BoardMapper boardMapper) {
        this.boardService = boardService;
        this.boardMapper = boardMapper;
    }

    @GetMapping
    public List<BoardDto> boardLists(UUID userId) {
        return boardService.listBoards(userId) // Call out list board service
                .stream()
                .map(boardMapper::toDto) // process each item in board list by using map, converting it
                // from board to dto
                .toList(); // then returning it as a list of board dtos
    }

    @PostMapping
    public BoardDto createBoard(@PathVariable("user_id") UUID userId, @RequestBody BoardDto boardDto) {


        Board createdBoard = boardService.createBoard(
                userId,
                boardMapper.fromDto(boardDto) // Converts Board Dto into a Board Entity
        );
        // Convert saved entity back into a DTO and return back to client
        return boardMapper.toDto(createdBoard);
    }

    @GetMapping(path = "/{board_id}")
    public Optional<BoardDto> getBoard(@PathVariable("user_id") UUID userId,
                                       @PathVariable("board_id") UUID boardId) {

        return boardService.getBoard(userId, boardId).map(boardMapper::toDto);

    }

    @PutMapping(path = "/{board_id}")
    public BoardDto updatedBoard(
            @PathVariable("user_id") UUID userId,
            @PathVariable("board_id") UUID boardId,
            @RequestBody BoardDto boardDto
    )
    {
        Board updatedBoard = boardService.updateBoard(
                userId,
                boardId,
                boardMapper.fromDto(boardDto)

        );

        return boardMapper.toDto(updatedBoard);
    }

    @DeleteMapping(path = "/{board_id}")
    public void deleteBoard(
            @PathVariable("user_id") UUID userId,
            @PathVariable("board_id") UUID boardId) {
        boardService.deleteBoard(userId, boardId);
    }
}
