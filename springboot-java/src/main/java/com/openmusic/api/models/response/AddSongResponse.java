
package com.openmusic.api.models.response;

/**
 * @author Ahmad Irfaan Hibatullah
 * @version $Id: AddSongResponse.java, v 0.1 2021‐11‐14 15.22 Ahmad Irfaan Hibatullah Exp $$
 */

public class AddSongResponse {

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