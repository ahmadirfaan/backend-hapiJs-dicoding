
package com.openmusic.api.exception;

import org.springframework.http.HttpStatus;

/**
 * @author Ahmad Irfaan Hibatullah
 * @version $Id: PathNotFoundException.java, v 0.1 2021‐10‐20 08.27 Ahmad Irfaan Hibatullah Exp $$
 */
public class PathNotFoundException extends ApplicationException {


    public PathNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }

    public PathNotFoundException() {
        super(HttpStatus.NOT_FOUND, "Path Not Found Exception");

    }
}