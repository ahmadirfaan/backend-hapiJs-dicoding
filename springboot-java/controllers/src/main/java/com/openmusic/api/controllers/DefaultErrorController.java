package com.openmusic.api.controllers;

import com.openmusic.api.exception.PathNotFoundException;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Ahmad Irfaan Hibatullah
 * @version $Id: DefaultErrorController.java, v 0.1 2021‐11‐14 11.24 Ahmad Irfaan Hibatullah Exp $$
 */

@Controller
public class DefaultErrorController implements ErrorController {

    @GetMapping("/error")
    public void handleError() {
        throw new PathNotFoundException();
    }


    @Override
    public String getErrorPath() {
        return "/error";
    }
}