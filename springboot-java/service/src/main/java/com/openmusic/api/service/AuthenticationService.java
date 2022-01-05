
package com.openmusic.api.service;

import com.openmusic.api.entities.database.Authentication;

/**
 * @author Ahmad Irfaan Hibatullah
 * @version $Id: AuthenticationService.java, v 0.1 2021‐10‐20 01.22 Ahmad Irfaan Hibatullah Exp $$
 */
public interface AuthenticationService {
    Authentication addRefreshToken(String token);
    Authentication verifyRefreshToken(String token);
    Boolean deleteRefreshToken(String token);
}