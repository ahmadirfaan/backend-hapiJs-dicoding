
package com.openmusic.api.models.request;

import javax.validation.constraints.NotBlank;

/**
 * @author Ahmad Irfaan Hibatullah
 * @version $Id: CollaborationRequest.java, v 0.1 2022‐01‐07 10.03 Ahmad Irfaan Hibatullah Exp $$
 */
public class CollaborationRequest {

    @NotBlank
    private String playlistId;

    @NotBlank
    private String userId;

    /**
     * Getter method for property playlistId.
     *
     * @return property value of playlistId
     */
    public String getPlaylistId() {
        return playlistId;
    }

    /**
     * Setter method for property playlistId.
     *
     * @param playlistId value to be assigned to property playlistId
     */

    public void setPlaylistId(String playlistId) {
        this.playlistId = playlistId;
    }

    /**
     * Getter method for property userId.
     *
     * @return property value of userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Setter method for property userId.
     *
     * @param userId value to be assigned to property userId
     */

    public void setUserId(String userId) {
        this.userId = userId;
    }
}