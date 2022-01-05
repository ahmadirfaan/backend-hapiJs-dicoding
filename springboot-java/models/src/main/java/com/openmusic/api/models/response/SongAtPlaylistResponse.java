package com.openmusic.api.models.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Ahmad Irfaan Hibatullah
 * @version $Id: SongAtPlaylistResponse.java, v 0.1 2022‐01‐05 17.15 Ahmad Irfaan Hibatullah Exp $$
 */
public class SongAtPlaylistResponse {

    @JsonProperty("id")
    private String songId;

    private String title;

    private String performer;

    /**
     * Getter method for property songId.
     *
     * @return property value of songId
     */
    public String getSongId() {
        return songId;
    }

    /**
     * Setter method for property songId.
     *
     * @param songId value to be assigned to property songId
     */

    public void setSongId(String songId) {
        this.songId = songId;
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
}