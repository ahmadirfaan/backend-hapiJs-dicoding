
package com.openmusic.api.models.request;

import javax.validation.constraints.NotBlank;

/**
 * @author Ahmad Irfaan Hibatullah
 * @version $Id: AddSongToPlaylist.java, v 0.1 2022‐01‐05 17.09 Ahmad Irfaan Hibatullah Exp $$
 */
public class AddSongToPlaylist {

    @NotBlank
    private String songId;

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
}