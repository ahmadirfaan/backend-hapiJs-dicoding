
package com.openmusic.api.models.request;

import javax.validation.constraints.NotBlank;

/**
 * @author Ahmad Irfaan Hibatullah
 * @version $Id: UserLoginRequest.java, v 0.1 2021‐11‐06 12.59 Ahmad Irfaan Hibatullah Exp $$
 */
public class UserLoginRequest {


    @NotBlank(message = "username tidak boleh ada yang kosong")
    private String username;

    @NotBlank(message = "password tidak boleh ada yang kosong")
    private String password;

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

    /**
     * Getter method for property password.
     *
     * @return property value of password
     */
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
}