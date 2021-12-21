package com.openmusic.api.controllers;

import com.openmusic.api.entities.database.Users;
import com.openmusic.api.models.request.UserRequest;
import com.openmusic.api.models.response.ResponseMessage;
import com.openmusic.api.models.response.UserResponse;
import com.openmusic.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Ahmad Irfaan Hibatullah
 * @version $Id: UserController.java, v 0.1 2021‐11‐08 19.24 Ahmad Irfaan Hibatullah Exp $$
 */

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<ResponseMessage<UserResponse>> addUser(@RequestBody @Valid UserRequest request) {
            Users users = userService.addUser(request);
            UserResponse userResponse = new UserResponse();
            userResponse.setUserId(users.getId());
            ResponseMessage<UserResponse> responseMessage = new ResponseMessage<>();
            responseMessage.setStatus("success");
            responseMessage.setMessage("User berhasil ditambahkan");
            responseMessage.setData(userResponse);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ResponseMessage<Users>> findById(@PathVariable String userId) {
            Users users = userService.findByUserId(userId);
            ResponseMessage<Users> responseMessage = new ResponseMessage<>();
            responseMessage.setMessage(null);
            responseMessage.setData(users);
            return ResponseEntity.ok(responseMessage);
    }
}