package com.openmusic.api.controller;

import com.openmusic.api.entities.database.Users;
import com.openmusic.api.exception.ClientException;
import com.openmusic.api.exception.EntityNotFoundException;
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
        try {
            Users users = userService.addUser(request);
            UserResponse userResponse = new UserResponse();
            userResponse.setUserId(users.getId());
            ResponseMessage<UserResponse> responseMessage = new ResponseMessage<>();
            responseMessage.setStatus("success");
            responseMessage.setMessage("User berhasil ditambahkan");
            responseMessage.setData(userResponse);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
        } catch (Exception e) {
            ResponseMessage<UserResponse> messageError = new ResponseMessage<>();
            messageError.setStatus("error");
            messageError.setData(null);
            if(e instanceof ClientException) {
                messageError.setMessage(((ClientException) e).getReason());
                return ResponseEntity.status(((ClientException) e).getStatus())
                        .body(messageError);
            } else {
                messageError.setMessage(e.getMessage());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(messageError);
            }
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseMessage<String>> findById(@PathVariable String userId) {
        try {
            Users users = userService.findByUserId(userId);
            ResponseMessage<String> responseMessage = new ResponseMessage<>();
            responseMessage.setMessage("User berhasil ditambahkan");
            responseMessage.setData(users.getId());
            return ResponseEntity.ok(responseMessage);
        } catch (Exception e) {
            ResponseMessage<String> messageError = new ResponseMessage<>();
            messageError.setStatus("error");
            messageError.setData(null);
            if(e instanceof ClientException) {
                messageError.setMessage(((ClientException) e).getReason());
                return ResponseEntity.status(((ClientException) e).getStatus())
                        .body(messageError);
            } else if(e instanceof EntityNotFoundException) {
                messageError.setMessage(((EntityNotFoundException) e).getReason());
                return ResponseEntity.status(((EntityNotFoundException) e).getStatus())
                        .body(messageError);
            }
            else {
                messageError.setMessage(e.getMessage());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(messageError);
            }
        }
    }
}