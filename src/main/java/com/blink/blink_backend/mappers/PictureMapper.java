package com.blink.blink_backend.mappers;

import com.blink.blink_backend.dto.PictureDto;
import com.blink.blink_backend.entities.Picture;

public interface PictureMapper {

    Picture fromDto(PictureDto pictureDto);

    PictureDto toDto(Picture picture);
}
