package com.blink.blink_backend.repositories;

import com.blink.blink_backend.entities.Picture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PictureRepository extends JpaRepository<Picture, UUID> {

}
