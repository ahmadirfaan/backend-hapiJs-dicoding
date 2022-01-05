
package com.openmusic.api.controllers;

import com.openmusic.api.entities.database.Playlists;
import com.openmusic.api.models.request.PlaylistRequest;
import com.openmusic.api.models.response.PlaylistOwnerResponse;
import com.openmusic.api.models.response.ResponseMessage;
import com.openmusic.api.service.CollaborationService;
import com.openmusic.api.service.PlaylistService;
import com.openmusic.api.service.UserService;
import com.openmusic.api.service.util.JwtTokenUtil;
import com.openmusic.api.util.JwtExtractUserId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Ahmad Irfaan Hibatullah
 * @version $Id: PlaylistController.java, v 0.1 2022‐01‐05 13.08 Ahmad Irfaan Hibatullah Exp $$
 */

@RestController
@RequestMapping("/playlists")
public class PlaylistController {

    protected final PlaylistService playlistService;

    protected final CollaborationService collaborationService;

    private final static Logger LOGGER = LoggerFactory.getLogger(PlaylistController.class);

    @Autowired
    public PlaylistController(PlaylistService playlistService, UserService service, JwtTokenUtil jwtTokenUtil, CollaborationService collaborationService) {
        this.playlistService = playlistService;
        this.collaborationService = collaborationService;
    }

    @PostMapping
    public ResponseEntity<ResponseMessage<Map<String,String>>> postPlaylist(@RequestBody PlaylistRequest request,
                                                                            @RequestHeader("Authorization") String token) {
        String userId = JwtExtractUserId.extractUserIdFromToken(token);
        Playlists playlist = playlistService.addPlaylist(request.getName(), userId);
        ResponseMessage<Map<String,String>> responseMessage = new ResponseMessage<>();
        responseMessage.setStatus("success");
        responseMessage.setMessage("Playlist berhasil ditambahkan");
        Map<String,String> data = new HashMap<>();
        data.put("playlistId", playlist.getId());
        responseMessage.setData(data);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
    }

    @GetMapping
    public ResponseEntity<ResponseMessage<Map<String,Object>>> getAllPlaylist(
            @RequestHeader("Authorization") String token) {
        String userId = JwtExtractUserId.extractUserIdFromToken(token);
        List<PlaylistOwnerResponse> responseList = collaborationService.getAllPlaylist(userId);
        ResponseMessage<Map<String, Object>> message = new ResponseMessage<>();
        Map<String, Object> playlistMap = new HashMap<>();
        playlistMap.put("playlists", responseList);
        message.setMessage(null);
        message.setData(playlistMap);
        message.setStatus("success");
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }
}