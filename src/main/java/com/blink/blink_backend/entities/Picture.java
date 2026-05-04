package com.blink.blink_backend.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
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

    public Picture() {
    }

    public Picture(UUID id, LocalDateTime capturedAt, String imageUrl, String sourceLink, User user) {
        this.id = id;
        this.capturedAt = capturedAt;
        this.imageUrl = imageUrl;
        this.sourceLink = sourceLink;
        this.user = user;
    }

    public LocalDateTime getCapturedAt() {
        return capturedAt;
    }

    public void setCapturedAt(LocalDateTime capturedAt) {
        this.capturedAt = capturedAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getSourceLink() {
        return sourceLink;
    }

    public void setSourceLink(String sourceLink) {
        this.sourceLink = sourceLink;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Picture picture = (Picture) o;
        return Objects.equals(id, picture.id) && Objects.equals(imageUrl, picture.imageUrl) && Objects.equals(sourceLink, picture.sourceLink) && Objects.equals(capturedAt, picture.capturedAt) && Objects.equals(user, picture.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, imageUrl, sourceLink, capturedAt, user);
    }

    @Override
    public String toString() {
        return "Picture{" +
                "id=" + id +
                ", imageUrl='" + imageUrl + '\'' +
                ", sourceLink='" + sourceLink + '\'' +
                ", capturedAt=" + capturedAt +
                ", user=" + user +
                '}';
    }
}
