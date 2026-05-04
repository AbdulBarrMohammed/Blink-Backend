package com.blink.blink_backend.mappers;

import com.blink.blink_backend.dto.BoardDto;
import com.blink.blink_backend.entities.Board;

public interface BoardMapper {
    Board fromDto(BoardDto boardDto);

    BoardDto toDto(Board board);
}
