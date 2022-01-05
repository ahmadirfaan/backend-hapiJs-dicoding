package com.openmusic.api.models.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author Ahmad Irfaan Hibatullah
 * @version $Id: UserRequest.java, v 0.1 2021‐10‐20 08.07 Ahmad Irfaan Hibatullah Exp $$
 */
public class UserRequest {

    @NotBlank(message = "username tidak boleh ada yang kosong")
    private String username;

    @NotBlank(message = "username tidak boleh ada yang kosong")
    private String password;

    @NotNull
    private String fullName;

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

    /**
     * Getter method for property fullName.
     *
     * @return property value of fullName
     */
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
}