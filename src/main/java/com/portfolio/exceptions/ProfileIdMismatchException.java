package com.portfolio.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception when user is not found
 *
 * @author Jorge Díaz
 * @version 1.0.0
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Profile Id mismatch")
public class ProfileIdMismatchException extends Exception {
    public ProfileIdMismatchException() {
        super("Profile Id mismatch");
    }
}