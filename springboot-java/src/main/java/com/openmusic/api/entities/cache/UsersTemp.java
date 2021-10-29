
package com.openmusic.api.entities.cache;

import com.openmusic.api.entities.database.AbstractTable;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

/**
 * @author Ahmad Irfaan Hibatullah
 * @version $Id: UsersTemp.java, v 0.1 2021‐10‐19 19.05 Ahmad Irfaan Hibatullah Exp $$
 */

@RedisHash(value = "users", timeToLive = 3600)
public class UsersTemp extends AbstractTable<String> {

    @Indexed
    @Id
    private String id;

    private String username;

    private String password;

    private String fullName;

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

    public String getPassword() {
        return password;
    }

    /**
     * Setter method for property password.
     *
     * @param password value to be assigned to property password
     */

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    /**
     * Setter method for property fullName.
     *
     * @param fullName value to be assigned to property fullName
     */

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }
}