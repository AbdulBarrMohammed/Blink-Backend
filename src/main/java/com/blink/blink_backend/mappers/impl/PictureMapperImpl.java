package com.blink.blink_backend.mappers.impl;

import com.blink.blink_backend.dto.PictureDto;
import com.blink.blink_backend.entities.Picture;
import com.blink.blink_backend.mappers.PictureMapper;
import org.springframework.stereotype.Component;

@Component
public class PictureMapperImpl implements PictureMapper {
    @Override
    public Picture fromDto(PictureDto pictureDto) {
        return new Picture(
                pictureDto.id(),
                pictureDto.capturedAt(),
                pictureDto.imageUrl(),
                pictureDto.sourceLink(),
                null
        );
    }

    @Override
    public PictureDto toDto(Picture picture) {
        return new PictureDto(
                picture.getId(),
                picture.getImageUrl(),
                picture.getSourceLink(),
                picture.getCapturedAt()


        );
    }
}
