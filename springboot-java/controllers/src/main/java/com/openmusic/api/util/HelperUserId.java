
package com.openmusic.api.util;

import com.openmusic.api.entities.database.Users;
import com.openmusic.api.service.UserService;
import com.openmusic.api.service.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

/**
 * @author Ahmad Irfaan Hibatullah
 * @version $Id: HelperUserId.java, v 0.1 2022‐01‐05 13.44 Ahmad Irfaan Hibatullah Exp $$
 */

@Component
public class HelperUserId {


    protected static UserService service = null;
    protected static JwtTokenUtil jwtTokenUtil = null;

    @Autowired
    public HelperUserId(UserService userService, JwtTokenUtil tokenUtil) {
        service = userService;
        jwtTokenUtil = tokenUtil;
    }

    public static String extractUserIdFromToken(User user) {
        Users users = service.verifyUsername(user.getUsername());
        return users.getId();
    }
}