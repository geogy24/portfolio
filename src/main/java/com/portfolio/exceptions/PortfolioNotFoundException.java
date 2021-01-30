package com.portfolio.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception when user is not found
 *
 * @author Jorge Díaz
 * @version 1.0.0
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Portfolio not found")
public class PortfolioNotFoundException extends Exception {
    public PortfolioNotFoundException() {
        super("Portfolio not found");
    }
}