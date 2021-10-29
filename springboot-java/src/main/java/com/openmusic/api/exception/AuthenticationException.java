/**
 * Dana.id.
 * Copyright (c) 2017‐2021 All Rights Reserved.
 */
package com.openmusic.api.exception;

import org.springframework.http.HttpStatus;

/**
 * @author Ahmad Irfaan Hibatullah
 * @version $Id: AuthenticationException.java, v 0.1 2021‐10‐20 08.41 Ahmad Irfaan Hibatullah Exp $$
 */
public class AuthenticationException extends ApplicationException {

    public AuthenticationException(String message) {
        super(HttpStatus.UNAUTHORIZED, message);
    }

    public AuthenticationException() {
        super(HttpStatus.UNAUTHORIZED, "Unauthorized Exception");
    }
}