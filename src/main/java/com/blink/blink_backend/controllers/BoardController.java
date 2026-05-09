package com.blink.blink_backend.controllers;

import com.blink.blink_backend.dto.BoardDto;
import com.blink.blink_backend.entities.Board;
import com.blink.blink_backend.mappers.BoardMapper;
import com.blink.blink_backend.services.BoardService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "/board")
public class BoardController {

    private final BoardService boardService;
    private final BoardMapper boardMapper;

    public BoardController(BoardService boardService, BoardMapper boardMapper) {
        this.boardService = boardService;
        this.boardMapper = boardMapper;
    }

    @GetMapping
    public List<BoardDto> boardLists() {
        return boardService.listBoard() // Call out list board service
                .stream()
                .map(boardMapper::toDto) // process each item in board list by using map, converting it
                // from board to dto
                .toList(); // then returning it as a list of board dtos
    }

    @PostMapping
    public BoardDto createBoard(@RequestBody BoardDto boardDto) {

        Board createdBoard = boardService.createBoard(
                boardMapper.fromDto(boardDto) // Converts Board Dto into a Board Entity
        );
        // Convert saved entity back into a DTO and return back to client
        return boardMapper.toDto(createdBoard);
    }

    @GetMapping(path = "/{board_id}")
    public Optional<BoardDto> getBoard(@PathVariable("board_id") UUID boardId) {
        return boardService.getBoard(boardId).map(boardMapper::toDto);

    }

    @PutMapping(path = "/{board_id}")
    public BoardDto updatedBoard(
            @PathVariable("board_id") UUID boardId,
            @RequestBody BoardDto boardDto
    )
    {
        Board updatedBoard = boardService.updateBoard(
                boardId,
                boardMapper.fromDto(boardDto)

        );

        return boardMapper.toDto(updatedBoard);
    }

    @DeleteMapping(path = "/{board_id}")
    public void deleteBoard(@PathVariable("board_id") UUID boardId) {
        boardService.deleteBoard(boardId);
    }
}
