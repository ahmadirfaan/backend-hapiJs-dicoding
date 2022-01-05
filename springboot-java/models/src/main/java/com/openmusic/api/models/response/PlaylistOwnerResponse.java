
package com.openmusic.api.models.response;

/**
 * @author Ahmad Irfaan Hibatullah
 * @version $Id: PlaylistOwnerResponse.java, v 0.1 2022‐01‐05 14.44 Ahmad Irfaan Hibatullah Exp $$
 */
public class PlaylistOwnerResponse {

    private String id;
    private String name;
    private String username;

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

    /**
     * Getter method for property username.
     *
     * @return property value of username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Setter method for property username.
     *
     * @param username value to be assigned to property username
     */

    public void setUsername(String username) {
        this.username = username;
    }
}