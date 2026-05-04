package com.blink.blink_backend.mappers.impl;

import com.blink.blink_backend.dto.BoardItemDto;
import com.blink.blink_backend.entities.BoardItem;
import com.blink.blink_backend.mappers.BoardItemMapper;
import org.springframework.stereotype.Component;

// Mapper converts one object to another

@Component
public class BoardItemMapperImpl implements BoardItemMapper {
    @Override
    public BoardItem fromDto(BoardItemDto boardItemDto) {
        return new BoardItem(
                boardItemDto.x(),
                boardItemDto.id(),
                boardItemDto.y(),
                null,
                null
        );
    }

    @Override
    public BoardItemDto toDto(BoardItem boardItem) {
        return new BoardItemDto(
                boardItem.getId(),
                boardItem.getX(),
                boardItem.getY()
        );
    }
}
