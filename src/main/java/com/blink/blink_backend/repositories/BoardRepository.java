package com.blink.blink_backend.repositories;

import com.blink.blink_backend.entities.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BoardRepository extends JpaRepository<Board, UUID> {
}
