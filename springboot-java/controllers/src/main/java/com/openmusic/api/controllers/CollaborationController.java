
package com.openmusic.api.controllers;

import com.openmusic.api.entities.database.Collaborations;
import com.openmusic.api.entities.database.Playlists;
import com.openmusic.api.models.request.CollaborationRequest;
import com.openmusic.api.models.response.ResponseMessage;
import com.openmusic.api.service.CollaborationService;
import com.openmusic.api.service.PlaylistService;
import com.openmusic.api.util.HelperUserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Ahmad Irfaan Hibatullah
 * @version $Id: CollaborationController.java, v 0.1 2022‐01‐07 09.31 Ahmad Irfaan Hibatullah Exp $$
 */

@RestController
@RequestMapping("/collaborations")
public class CollaborationController {

    protected final CollaborationService collaborationService;
    protected final PlaylistService playlistService;

    @Autowired
    public CollaborationController(CollaborationService collaborationService, PlaylistService playlistService) {
        this.collaborationService = collaborationService;
        this.playlistService = playlistService;
    }

    @PostMapping
    public ResponseEntity<ResponseMessage<Map<String, String>>> addCollaborations(@RequestBody
                                                                                  @Valid CollaborationRequest
                                                                                          request) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userId = HelperUserId.extractUserIdFromToken(user);
        Playlists playlists = playlistService.verifyPlaylistOwner(request.getPlaylistId(), userId);
        Collaborations collaboration = collaborationService.addCollaboration(request.getPlaylistId(), request.getUserId());
        ResponseMessage<Map<String, String>> responseMessage = new ResponseMessage<>();
        responseMessage.setStatus("success");
        responseMessage.setMessage("Playlist berhasil ditambahkan");
        Map<String, String> data = new HashMap<>();
        data.put("collaborationId", collaboration.getId());
        responseMessage.setData(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
    }

    @DeleteMapping
    public ResponseEntity<ResponseMessage<String>> deleteCollaboration(@RequestBody @Valid CollaborationRequest
                                                                                   request) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userId = HelperUserId.extractUserIdFromToken(user);
        ResponseMessage<String> responseMessage = new ResponseMessage<>();
        Playlists playlists = playlistService.verifyPlaylistOwner(request.getPlaylistId(), userId);
        Collaborations collaborations = collaborationService.deleteCollaboration(request.getPlaylistId(), request.getUserId());
        responseMessage.setMessage("Kolaborasi berhasil dihapus");
        responseMessage.setData(null);
        responseMessage.setStatus("success");
        return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
    }
}