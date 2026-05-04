package com.blink.blink_backend.controllers;

import com.blink.blink_backend.dto.BoardDto;
import com.blink.blink_backend.mappers.BoardMapper;
import com.blink.blink_backend.services.BoardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
        return boardService.listBoard()
                .stream()
                .map(boardMapper::toDto)
                .toList();
    }
}
