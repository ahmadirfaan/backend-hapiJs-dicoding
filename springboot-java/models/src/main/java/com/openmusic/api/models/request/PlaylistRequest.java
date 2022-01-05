
package com.openmusic.api.models.request;

import javax.validation.constraints.NotBlank;

/**
 * @author Ahmad Irfaan Hibatullah
 * @version $Id: PlaylistRequest.java, v 0.1 2022‐01‐05 13.21 Ahmad Irfaan Hibatullah Exp $$
 */
public class PlaylistRequest {

    @NotBlank
    private String name;

    /**
     * Getter method for property name.
     *
     * @return property value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method for property name.
     *
     * @param name value to be assigned to property name
     */

    public void setName(String name) {
        this.name = name;
    }
}