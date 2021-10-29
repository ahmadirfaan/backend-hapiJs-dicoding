package com.openmusic.api.entities.cache;

import com.openmusic.api.entities.database.Users;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

/**
 * @author Ahmad Irfaan Hibatullah
 * @version $Id: PlaylistsTemp.java, v 0.1 2021‐10‐19 19.08 Ahmad Irfaan Hibatullah Exp $$
 */

@RedisHash(value = "playlist", timeToLive = 3600)
public class PlaylistsTemp {

    @Id
    private String id;

    private String name;

    @Indexed
    private Users owner;

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
     * Getter method for property owner.
     *
     * @return property value of owner
     */
    public Users getOwner() {
        return owner;
    }

    /**
     * Setter method for property owner.
     *
     * @param owner value to be assigned to property owner
     */

    public void setOwner(Users owner) {
        this.owner = owner;
    }
}