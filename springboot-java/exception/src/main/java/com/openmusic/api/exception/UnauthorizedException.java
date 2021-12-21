
package com.openmusic.api.exception;

import org.springframework.http.HttpStatus;

/**
 * @author Ahmad Irfaan Hibatullah
 * @version $Id: UnauthorizedException.java, v 0.1 2021‐10‐20 08.32 Ahmad Irfaan Hibatullah Exp $$
 */
public class UnauthorizedException extends ApplicationException {

    public UnauthorizedException(String message) {
        super(HttpStatus.FORBIDDEN, message);
    }

    public UnauthorizedException(HttpStatus status, String message) {
        super(HttpStatus.FORBIDDEN, message);
    }
}