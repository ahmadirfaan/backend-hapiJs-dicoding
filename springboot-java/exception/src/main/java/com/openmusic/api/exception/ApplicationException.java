
package com.openmusic.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * @author Ahmad Irfaan Hibatullah
 * @version $Id: ApplicationException.java, v 0.1 2021‐10‐20 00.54 Ahmad Irfaan Hibatullah Exp $$
 */
public class ApplicationException extends ResponseStatusException {

    private static final long serialVersionUID = 3856634681991594109L;

    public ApplicationException(String message) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, message);
    }

    public ApplicationException() {
        super(HttpStatus.INTERNAL_SERVER_ERROR, "Application Error");
    }

    public ApplicationException(HttpStatus status, String message) {
        super(status, message);
    }
}