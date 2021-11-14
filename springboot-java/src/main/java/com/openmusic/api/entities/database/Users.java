package com.openmusic.api.entities.database;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @author Ahmad Irfaan Hibatullah
 * @version $Id: Users.java, v 0.1 2021‐10‐19 19.05 Ahmad Irfaan Hibatullah Exp $$
 */

@Entity
@Table(name = "users")
public class Users extends AbstractTable<String> {

    @Id
    @GenericGenerator(name = "id_user", strategy = "uuid")
    @GeneratedValue(generator = "id_user", strategy = GenerationType.IDENTITY)
    private String id;

    private String username;

    @JsonIgnore
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