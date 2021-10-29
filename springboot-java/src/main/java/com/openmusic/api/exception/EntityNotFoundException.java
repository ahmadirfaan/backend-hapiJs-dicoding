
package com.openmusic.api.exception;

import org.springframework.http.HttpStatus;

/**
 * @author Ahmad Irfaan Hibatullah
 * @version $Id: EntityNotFoundException.java, v 0.1 2021‐10‐20 00.53 Ahmad Irfaan Hibatullah Exp $$
 */
public class EntityNotFoundException extends ApplicationException {



    public EntityNotFoundException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }

    public EntityNotFoundException() {
        super(HttpStatus.BAD_REQUEST, "Entity Not Found");
    }
}