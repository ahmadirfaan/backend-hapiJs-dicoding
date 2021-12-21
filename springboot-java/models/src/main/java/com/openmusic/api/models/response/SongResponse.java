package com.openmusic.api.models.response;

import com.openmusic.api.entities.database.Songs;

import java.time.LocalDateTime;

/**
 * @author Ahmad Irfaan Hibatullah
 * @version $Id: SongResponse.java, v 0.1 2021‐11‐14 15.53 Ahmad Irfaan Hibatullah Exp $$
 */
public class SongResponse {

    private String id;

    private String title;

    private Integer year;

    private String performer;

    private String genre;

    private Integer duration;

    private LocalDateTime insertedAt;

    private LocalDateTime updatedAt;

    public Songs convertSongResponseToSong() {
        Songs song = new Songs();
        song.setId(id);
        song.setDuration(duration);
        song.setGenre(genre);
        song.setPerformer(performer);
        song.setTitle(title);
        song.setYear(year);
        song.setCreatedDate(insertedAt);
        song.setUpdatedDate(updatedAt);
        return song;
    }


    /**
     * Getter method for property id.
     *
     * @return property value of id
     */
    public String getId() {
        return id;
    }

    /**
     * Setter method for property id.
     *
     * @param id value to be assigned to property id
     */

    public void setId(String id) {
        this.id = id;
    }

    /**
     * Getter method for property title.
     *
     * @return property value of title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Setter method for property title.
     *
     * @param title value to be assigned to property title
     */

    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Getter method for property year.
     *
     * @return property value of year
     */
    public Integer getYear() {
        return year;
    }

    /**
     * Setter method for property year.
     *
     * @param year value to be assigned to property year
     */

    public void setYear(Integer year) {
        this.year = year;
    }

    /**
     * Getter method for property performer.
     *
     * @return property value of performer
     */
    public String getPerformer() {
        return performer;
    }

    /**
     * Setter method for property performer.
     *
     * @param performer value to be assigned to property performer
     */

    public void setPerformer(String performer) {
        this.performer = performer;
    }

    /**
     * Getter method for property genre.
     *
     * @return property value of genre
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Setter method for property genre.
     *
     * @param genre value to be assigned to property genre
     */

    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * Getter method for property duration.
     *
     * @return property value of duration
     */
    public Integer getDuration() {
        return duration;
    }

    /**
     * Setter method for property duration.
     *
     * @param duration value to be assigned to property duration
     */

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    /**
     * Getter method for property insertedAt.
     *
     * @return property value of insertedAt
     */
    public LocalDateTime getInsertedAt() {
        return insertedAt;
    }

    /**
     * Setter method for property insertedAt.
     *
     * @param insertedAt value to be assigned to property insertedAt
     */

    public void setInsertedAt(LocalDateTime insertedAt) {
        this.insertedAt = insertedAt;
    }

    /**
     * Getter method for property updatedAt.
     *
     * @return property value of updatedAt
     */
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Setter method for property updatedAt.
     *
     * @param updatedAt value to be assigned to property updatedAt
     */

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}