package com.blink.blink_backend.repositories;

import com.blink.blink_backend.entities.Board;
import com.blink.blink_backend.entities.BoardItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BoardRepository extends JpaRepository<Board, UUID> {

    List<Board> findByUserId(UUID userId);
    Optional<Board> findByUserIdAndId(UUID userId, UUID id);
    void deleteByUserIdAndId(UUID userId, UUID id);
}
