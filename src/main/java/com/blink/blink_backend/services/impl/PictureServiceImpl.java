package com.blink.blink_backend.services.impl;

import com.blink.blink_backend.entities.Picture;
import com.blink.blink_backend.entities.User;
import com.blink.blink_backend.repositories.PictureRepository;
import com.blink.blink_backend.repositories.UserRepository;
import com.blink.blink_backend.services.PictureService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class PictureServiceImpl implements PictureService {

    private final PictureRepository pictureRepository;
    private final UserRepository userRepository;

    public PictureServiceImpl(PictureRepository pictureRepository, UserRepository userRepository) {
        this.pictureRepository = pictureRepository;
        this.userRepository = userRepository;
    }
    @Override
    public List<Picture> listPicture() {
        return pictureRepository.findAll();
    }

    @Override
    public Picture createPicture(Picture picture, UUID userId) {

        if (picture.getId() != null) {
            throw new IllegalArgumentException("Picture already has an ID");
        }

        if (picture.getImageUrl() == null || picture.getImageUrl().isBlank()) {
            throw new IllegalArgumentException("Picture must have a image url");
        }

        LocalDateTime createdAt = LocalDateTime.now();
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Invalid User ID provided"));;
        return  pictureRepository.save(
                new Picture(
                        picture.getId(),
                        createdAt,
                        picture.getImageUrl(),
                        picture.getSourceLink(),
                        user

                )
        );

    }

    @Override
    public Optional<Picture> getPicture(UUID id) {
        return Optional.empty();
    }

    @Override
    public void deletePicture(UUID pictureId) {

    }
}
