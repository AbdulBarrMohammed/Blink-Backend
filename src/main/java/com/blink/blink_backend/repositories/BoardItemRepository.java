package com.blink.blink_backend.repositories;

import com.blink.blink_backend.entities.Board;
import com.blink.blink_backend.entities.BoardItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

// This is the repository, its job is to talk to the database
public interface BoardItemRepository extends JpaRepository<BoardItem, UUID> {
    List<BoardItem> findByBoardId(UUID boardId);
    Optional<Board> findByBoardIdAndId(UUID boardId, UUID id);

}
