package com.blink.blink_backend.controllers;

import com.blink.blink_backend.dto.PictureDto;
import com.blink.blink_backend.entities.Picture;
import com.blink.blink_backend.mappers.PictureMapper;
import com.blink.blink_backend.services.PictureService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/picture")
public class PictureController {

    private final PictureService pictureService;
    private final PictureMapper pictureMapper;

    public PictureController(PictureService pictureService, PictureMapper pictureMapper) {
        this.pictureService = pictureService;
        this.pictureMapper = pictureMapper;
    }

    @GetMapping
    public List<PictureDto> pictureList() {
        return pictureService.listPicture()
                .stream()
                .map(pictureMapper::toDto)
                .toList();
    }

    @PostMapping
    public PictureDto createPicture(@RequestBody PictureDto pictureDto,
                                    @PathVariable("user_id") UUID userId) {
        Picture createdPicture = pictureService.createPicture(
                pictureMapper.fromDto(pictureDto),
                userId);

        return pictureMapper.toDto(createdPicture);
    }
 }
