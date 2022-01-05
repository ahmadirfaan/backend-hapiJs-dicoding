package com.openmusic.api.controllers;

import com.openmusic.api.entities.database.Authentication;
import com.openmusic.api.exception.ClientException;
import com.openmusic.api.models.request.RefreshTokenRequest;
import com.openmusic.api.models.request.UserLoginRequest;
import com.openmusic.api.models.response.ResponseMessage;
import com.openmusic.api.service.AuthenticationService;
import com.openmusic.api.service.UserService;
import com.openmusic.api.service.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Ahmad Irfaan Hibatullah
 * @version $Id: AuthenticationController.java, v 0.1 2021‐12‐22 17.14 Ahmad Irfaan Hibatullah Exp $$
 */

@RestController
@RequestMapping("/authentications")
public class AuthenticationController {

    protected final UserDetailsService usersDetailService;
    protected final AuthenticationService authenticationService;
    protected final JwtTokenUtil jwtTokenUtil;
    protected final UserService userService;

    @Autowired
    public AuthenticationController(UserDetailsService usersDetailService,
                                    AuthenticationService authenticationService,
                                    JwtTokenUtil jwtTokenUtil, UserService userService) {
        this.usersDetailService = usersDetailService;
        this.authenticationService = authenticationService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<ResponseMessage<Map<String, String>>> postAuthentication(@RequestBody @Valid UserLoginRequest request) {
        Boolean isLogin = userService.verifyUserCredentials(request);
        ResponseMessage<Map<String, String>> responseMessage = new ResponseMessage<>();
        if (isLogin) {

            //create skeleton of Response Message
            responseMessage.setStatus("success");
            responseMessage.setMessage("Authentication berhasil ditambahkan");

            //generate user details
            UserDetails userDetails = usersDetailService.loadUserByUsername(request.getUsername());

            //generate access token and refresh token
            String accessToken = jwtTokenUtil.generateAccessToken(userDetails);
            String refreshToken = jwtTokenUtil.generateRefreshToken(userDetails);

            //response message data
            Map<String, String> mapToken = new HashMap<>();
            mapToken.put("accessToken", accessToken);
            mapToken.put("refreshToken", refreshToken);

            //add refresh token to database
            authenticationService.addRefreshToken(refreshToken);

            //set data to responseMessage
            responseMessage.setData(mapToken);

            return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
        } else {
            responseMessage.setStatus("fail");
            responseMessage.setMessage("password salah");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseMessage);

        }
    }

    @PutMapping
    public ResponseEntity<ResponseMessage<Map<String, String>>> putAuthentication(@RequestBody @Valid RefreshTokenRequest request) {
        String refreshToken = request.getRefreshToken();
        //extract username in Refresh Token
        String userName = jwtTokenUtil.getUserNameFromRefreshToken(refreshToken);
        UserDetails userDetails = usersDetailService.loadUserByUsername(userName);

        //verify valid of refreshToken
        Boolean isValid = jwtTokenUtil.validateRefreshToken(refreshToken, userDetails);
        if (isValid) {
            //verify refreshToken in Database
            Authentication authentication = authenticationService.verifyRefreshToken(refreshToken);
            if (authentication != null) {
                //generate Access Token
                ResponseMessage<Map<String, String>> responseMessage = new ResponseMessage<>();
                responseMessage.setStatus("success");
                responseMessage.setMessage("Access Token berhasil diperbarui");
                Map<String, String> mapToken = new HashMap<>();
                String accessToken = jwtTokenUtil.generateAccessToken(userDetails);
                mapToken.put("accessToken", accessToken);
                responseMessage.setData(mapToken);
                return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
            }

        }
        throw new ClientException("Refresh Token tidak valid");
    }

    @DeleteMapping
    public ResponseEntity<ResponseMessage<String>> deleteAuthentication(@RequestBody @Valid RefreshTokenRequest request) {
        String refreshToken = request.getRefreshToken();

        //extract username in Refresh Token
        String userName = jwtTokenUtil.getUserNameFromRefreshToken(refreshToken);
        UserDetails userDetails = usersDetailService.loadUserByUsername(userName);

        //verify valid of refreshToken
        Boolean isValid = jwtTokenUtil.validateRefreshToken(refreshToken, userDetails);
        if (isValid) {
            //delete refreshToken in Database
            Boolean isDeleted = authenticationService.deleteRefreshToken(refreshToken);
            if (isDeleted) {
                //generate Response Message
                ResponseMessage<String> responseMessage = new ResponseMessage<>();
                responseMessage.setStatus("success");
                responseMessage.setMessage("Refresh token berhasil dihapus");
                responseMessage.setData(null);
                return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
            }
        }
        throw new ClientException("Refresh Token tidak valid");
    }

}