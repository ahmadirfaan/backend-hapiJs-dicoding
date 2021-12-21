package com.openmusic.api.exception;

import org.springframework.http.HttpStatus;

/**
 * @author Ahmad Irfaan Hibatullah
 * @version $Id: ClientException.java, v 0.1 2021‐10‐20 08.58 Ahmad Irfaan Hibatullah Exp $$
 */
public class ClientException extends ApplicationException {

    public ClientException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }

    public ClientException(HttpStatus status, String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}