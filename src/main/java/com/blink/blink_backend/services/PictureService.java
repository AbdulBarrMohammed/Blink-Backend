package com.blink.blink_backend.services;

import com.blink.blink_backend.entities.Picture;
import com.blink.blink_backend.entities.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PictureService {
    List<Picture> listPicture();
    Picture createPicture(Picture picture);
    Optional<Picture> getPicture(UUID id);
    //Picture updatePicture(UUID pictureId, Picture picture);
    void deletePicture(UUID pictureId);
}
