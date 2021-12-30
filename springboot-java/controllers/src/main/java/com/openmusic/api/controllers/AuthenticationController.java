package com.openmusic.api.controllers;

import com.openmusic.api.models.request.RefreshTokenRequest;
import com.openmusic.api.models.request.UserLoginRequest;
import com.openmusic.api.models.request.UserRequest;
import com.openmusic.api.models.response.ResponseMessage;
import com.openmusic.api.models.response.UserResponse;
import com.openmusic.api.service.AuthenticationService;
import com.openmusic.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Ahmad Irfaan Hibatullah
 * @version $Id: AuthenticationController.java, v 0.1 2021‐12‐22 17.14 Ahmad Irfaan Hibatullah Exp $$
 */

@RestController("/authentication")
public class AuthenticationController {

    protected final UserService userService;
    protected final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(UserService userService, AuthenticationService authenticationService) {
        this.userService = userService;
        this.authenticationService = authenticationService;
    }

    @PostMapping
    public ResponseEntity<ResponseMessage<UserResponse>> postAuthentication(@RequestBody @Valid UserLoginRequest request) {
//        boolean isPassed = userService.verifyUserCredentials(request);
//        if(isPassed) {
//            return null;
//        }
//        return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
        return null;
    }

    @PutMapping
    public ResponseEntity<ResponseMessage<UserResponse>> putAuthentication(@RequestBody @Valid RefreshTokenRequest request) {
        return null;
    }

    @DeleteMapping
    public ResponseEntity<ResponseMessage<UserResponse>> deleteAuthentication(@RequestBody @Valid UserRequest request) {
        return null;
    }

}