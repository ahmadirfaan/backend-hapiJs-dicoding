
package com.openmusic.api.controllers;

import com.openmusic.api.entities.database.PlaylistSong;
import com.openmusic.api.entities.database.Playlists;
import com.openmusic.api.models.request.AddSongToPlaylist;
import com.openmusic.api.models.request.PlaylistRequest;
import com.openmusic.api.models.response.*;
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
import java.util.ArrayList;
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


    @Autowired
    public PlaylistController(PlaylistService playlistService, CollaborationService collaborationService) {
        this.playlistService = playlistService;
        this.collaborationService = collaborationService;
    }


    @PostMapping
    public ResponseEntity<ResponseMessage<Map<String, String>>> postPlaylist(@RequestBody @Valid PlaylistRequest request) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userId = HelperUserId.extractUserIdFromToken(user);
        Playlists playlist = playlistService.addPlaylist(request.getName(), userId);
        ResponseMessage<Map<String, String>> responseMessage = new ResponseMessage<>();
        responseMessage.setStatus("success");
        responseMessage.setMessage("Playlist berhasil ditambahkan");
        Map<String, String> data = new HashMap<>();
        data.put("playlistId", playlist.getId());
        responseMessage.setData(data);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
    }

    @GetMapping
    public ResponseEntity<ResponseMessage<Map<String, Object>>> getAllPlaylist() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userId = HelperUserId.extractUserIdFromToken(user);
        List<PlaylistOwnerResponse> responseList = collaborationService.getAllPlaylist(userId);
        ResponseMessage<Map<String, Object>> message = new ResponseMessage<>();
        Map<String, Object> playlistMap = new HashMap<>();
        playlistMap.put("playlists", responseList);
        message.setMessage(null);
        message.setData(playlistMap);
        message.setStatus("success");
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    @DeleteMapping("/{playlistId}")
    public ResponseEntity<ResponseMessage<String>> deletePlaylistById(@PathVariable String playlistId) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userId = HelperUserId.extractUserIdFromToken(user);
        ResponseMessage<String> responseMessage = new ResponseMessage<>();
        Playlists playlists = playlistService.verifyPlaylistOwner(playlistId, userId);
        playlistService.deletePlaylistById(playlists.getId());
        responseMessage.setMessage("Playlist berhasil dihapus");
        responseMessage.setData(null);
        responseMessage.setStatus("success");
        return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
    }

    @PostMapping("/{playlistId}/songs")
    public ResponseEntity<ResponseMessage<String>> addSongToPlaylist(@PathVariable String playlistId,
                                                                     @RequestBody @Valid AddSongToPlaylist request) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userId = HelperUserId.extractUserIdFromToken(user);
        ResponseMessage<String> responseMessage = new ResponseMessage<>();
        Playlists playlists = playlistService.verifyPlaylistOwner(playlistId, userId);
        playlistService.addSongToPlaylist(playlists.getId(), request.getSongId());
        responseMessage.setMessage("Lagu berhasil ditambahkan ke playlist");
        responseMessage.setData(null);
        responseMessage.setStatus("success");
        return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
    }

    @GetMapping("/{playlistId}/songs")
    public ResponseEntity<ResponseMessage<Map<String, Object>>> getSongAtPlaylist(@PathVariable String playlistId) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userId = HelperUserId.extractUserIdFromToken(user);
        ResponseMessage<Map<String, Object>> responseMessage = new ResponseMessage<>();
        Playlists playlists = playlistService.verifyPlaylistOwner(playlistId, userId);
        List<PlaylistSong> playlistSongList = playlistService.getSongAtPlaylist(playlists.getId());
        List<SongAtPlaylistResponse> data = new ArrayList<>();
        playlistSongList.forEach(ps -> {
            SongAtPlaylistResponse response = new SongAtPlaylistResponse();
            response.setTitle(ps.getSong().getTitle());
            response.setSongId(ps.getSong().getId());
            response.setPerformer(ps.getSong().getPerformer());
            data.add(response);
        });
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("songs", data);
        responseMessage.setMessage("Playlist Berhasil Ditambahkan");
        responseMessage.setData(dataMap);
        responseMessage.setStatus("success");
        return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
    }

    @DeleteMapping("/{playlistId}/songs")
    public ResponseEntity<ResponseMessage<String>> deleteSongAtPlaylist(@PathVariable String playlistId,
                                                                                     @RequestBody @Valid AddSongResponse request) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userId = HelperUserId.extractUserIdFromToken(user);
        ResponseMessage<String> responseMessage = new ResponseMessage<>();
        Playlists playlists = playlistService.verifyPlaylistOwner(playlistId, userId);
        PlaylistSong playlistSong = playlistService.deleteSongAtPlaylist(playlists.getId(), request.getSongId());
        responseMessage.setMessage("Lagu berhasil dihapus dari playlist");
        responseMessage.setData(null);
        responseMessage.setStatus("success");
        return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
    }


}