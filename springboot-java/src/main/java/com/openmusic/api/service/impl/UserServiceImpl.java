
package com.openmusic.api.service.impl;

import com.openmusic.api.entities.database.Users;
import com.openmusic.api.models.request.UserRequest;
import com.openmusic.api.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author Ahmad Irfaan Hibatullah
 * @version $Id: UserServiceImpl.java, v 0.1 2021‐10‐20 10.27 Ahmad Irfaan Hibatullah Exp $$
 */
@Service
public class UserServiceImpl implements UserService {

    @Override
    public Users addUser(UserRequest request) {
        return null;
    }

    @Override
    public Users verifyUsername(String username) {
        return null;
    }

    @Override
    public Users findByUserId(String userId) {
        return null;
    }

    @Override
    public Users verifyUserCredentials(String username, String password) {
        return null;
    }
}