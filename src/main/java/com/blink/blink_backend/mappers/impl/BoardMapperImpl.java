package com.blink.blink_backend.mappers.impl;

import com.blink.blink_backend.dto.BoardDto;
import com.blink.blink_backend.entities.Board;
import com.blink.blink_backend.mappers.BoardItemMapper;
import com.blink.blink_backend.mappers.BoardMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component
public class BoardMapperImpl implements BoardMapper {

    private final BoardItemMapper boardItemMapper;

    public BoardMapperImpl(BoardItemMapper boardItemMapper) {
        this.boardItemMapper = boardItemMapper;
    }


    @Override
    public Board fromDto(BoardDto boardDto) {
        return new Board(
                boardDto.createdAt(),
                boardDto.id(),
                boardDto.title(),
                Optional.ofNullable(boardDto.boardItems())
                        .map(boardItems -> boardItems.stream()
                                .map(boardItemMapper::fromDto)
                                .toList()
                        ).orElse(null),
                null

        );
    }

    @Override
    public BoardDto toDto(Board board) {
        return new BoardDto(
                board.getId(),
                board.getTitle(),
                board.getCreatedAt(),
                Optional.ofNullable(board.getBoardItems())
                        .map(boardItems ->
                                boardItems.stream().map(boardItemMapper::toDto).toList()).orElse(null)

        );
    }
}
