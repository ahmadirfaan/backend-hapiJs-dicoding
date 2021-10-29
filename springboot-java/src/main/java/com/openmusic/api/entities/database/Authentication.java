
package com.openmusic.api.entities.database;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @author Ahmad Irfaan Hibatullah
 * @version $Id: Authentication.java, v 0.1 2021‐10‐19 19.07 Ahmad Irfaan Hibatullah Exp $$
 */
@Entity
@Table(name = "authentication")
public class Authentication extends AbstractTable<String>{

    @Id
    @GenericGenerator(name = "id_token", strategy = "uuid")
    @GeneratedValue(generator = "id_token", strategy = GenerationType.IDENTITY)
    private String id;

    private String token;

    @Override
    public String getId() {
        return id;
    }

    /**
     * Setter method for property id.
     *
     * @param id value to be assigned to property id
     */

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    /**
     * Setter method for property token.
     *
     * @param token value to be assigned to property token
     */

    public void setToken(String token) {
        this.token = token;
    }

}