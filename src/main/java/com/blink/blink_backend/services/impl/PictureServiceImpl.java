package com.blink.blink_backend.services.impl;

import com.blink.blink_backend.entities.Picture;
import com.blink.blink_backend.repositories.PictureRepository;
import com.blink.blink_backend.services.PictureService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class PictureServiceImpl implements PictureService {

    private final PictureRepository pictureRepository;

    public PictureServiceImpl(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }
    @Override
    public List<Picture> listPicture() {
        return pictureRepository.findAll();
    }

    @Override
    public Picture createPicture(Picture picture) {
        return null;
    }

    @Override
    public Optional<Picture> getPicture(UUID id) {
        return Optional.empty();
    }

    @Override
    public void deletePicture(UUID pictureId) {

    }
}
