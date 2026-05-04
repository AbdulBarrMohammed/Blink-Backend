package com.blink.blink_backend.mappers;


import com.blink.blink_backend.dto.BoardItemDto;
import com.blink.blink_backend.entities.BoardItem;

public interface BoardItemMapper {
    BoardItem fromDto(BoardItemDto boardItemDto);

    BoardItemDto toDto(BoardItem boardItem);
}
