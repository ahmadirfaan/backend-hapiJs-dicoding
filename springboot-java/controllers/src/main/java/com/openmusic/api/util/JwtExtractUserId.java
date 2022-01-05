
package com.openmusic.api.util;

import com.openmusic.api.entities.database.Users;
import com.openmusic.api.service.UserService;
import com.openmusic.api.service.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Ahmad Irfaan Hibatullah
 * @version $Id: JwtExtractUserId.java, v 0.1 2022‐01‐05 13.44 Ahmad Irfaan Hibatullah Exp $$
 */

@Component
public class JwtExtractUserId {


    protected static UserService service = null;
    protected static JwtTokenUtil jwtTokenUtil = null;

    @Autowired
    public JwtExtractUserId(UserService userService, JwtTokenUtil tokenUtil) {
        service = userService;
        jwtTokenUtil = tokenUtil;
    }

    public static String extractUserIdFromToken(String headersAuthorization) {
        int maxLength = headersAuthorization.length();
        String accessToken = headersAuthorization.substring(7, maxLength);
        String username = jwtTokenUtil.getUserNameFromAccessToken(accessToken);
        Users users = service.verifyUsername(username);
        return users.getId();
    }
}