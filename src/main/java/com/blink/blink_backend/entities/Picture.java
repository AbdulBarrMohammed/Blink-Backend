package com.blink.blink_backend.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "Pictures")
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    // Source link can be optional
    @Column(name = "source_link", nullable = true)
    private String sourceLink;

    @Column(name = "captured_at", nullable = false)
    private LocalDateTime capturedAt;


    //Many pictures belonging to one user
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

}
