package com.openmusic.api.service;

import com.openmusic.api.entities.database.Users;
import com.openmusic.api.models.request.UserLoginRequest;
import com.openmusic.api.models.request.UserRequest;

/**
 * @author Ahmad Irfaan Hibatullah
 * @version $Id: UserService.java, v 0.1 2021‐10‐20 08.09 Ahmad Irfaan Hibatullah Exp $$
 */
public interface UserService {
    Users addUser(UserRequest request);
    Users verifyUsername(String username);
    Users findByUserId(String userId);
    Boolean verifyUserCredentials(UserLoginRequest request);
}