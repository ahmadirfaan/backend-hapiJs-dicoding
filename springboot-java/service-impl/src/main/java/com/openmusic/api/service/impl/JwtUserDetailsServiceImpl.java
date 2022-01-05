
package com.openmusic.api.service.impl;

import com.openmusic.api.entities.database.Users;
import com.openmusic.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @author Ahmad Irfaan Hibatullah
 * @version $Id: JwtUserDetailsServiceImpl.java, v 0.1 2022‐01‐04 14.25 Ahmad Irfaan Hibatullah Exp $$
 */

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    private UserService userService;

    @Autowired
    public JwtUserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = userService.verifyUsername(username);
        if (users.getUsername().equals(username)) {
            return new User(users.getUsername(), users.getPassword(), new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}