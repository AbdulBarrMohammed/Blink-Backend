package com.blink.blink_backend.services.impl;

import com.blink.blink_backend.entities.Board;
import com.blink.blink_backend.repositories.BoardRepository;
import com.blink.blink_backend.services.BoardService;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
