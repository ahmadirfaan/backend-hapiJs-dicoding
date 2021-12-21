package com.openmusic.api.controllers;

import com.openmusic.api.entities.database.Songs;
import com.openmusic.api.models.request.SongRequest;
import com.openmusic.api.models.response.AddSongResponse;
import com.openmusic.api.models.response.GetAllSongResponse;
import com.openmusic.api.models.response.ResponseMessage;
import com.openmusic.api.models.response.SongResponse;
import com.openmusic.api.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Ahmad Irfaan Hibatullah
 * @version $Id: SongController.java, v 0.1 2021‐11‐14 10.41 Ahmad Irfaan Hibatullah Exp $$
 */

@RestController
@RequestMapping("/songs")
public class SongController {

    protected final SongService songService;

    @Autowired
    public SongController(SongService songService) {
        this.songService = songService;
    }

    @PostMapping
    public ResponseEntity<ResponseMessage<AddSongResponse>> addSong(@RequestBody @Valid SongRequest songRequest) {
        Songs songs = songService.addSong(songRequest);
        AddSongResponse addSongResponse = new AddSongResponse();
        addSongResponse.setSongId(songs.getId());
        ResponseMessage<AddSongResponse> responseMessage = new ResponseMessage<>();
        responseMessage.setStatus("success");
        responseMessage.setMessage("Lagu berhasil ditambahkan");
        responseMessage.setData(addSongResponse);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
    }

    @GetMapping
    public ResponseEntity<ResponseMessage<Map<String, List<GetAllSongResponse>>>> getAllSong() {
        List<GetAllSongResponse> songsList = songService.findAllSong();
        ResponseMessage<Map<String, List<GetAllSongResponse>>> message = new ResponseMessage<>();
        Map<String, List<GetAllSongResponse>> songsMap = new HashMap<>();
        songsMap.put("songs", songsList);
        message.setMessage(null);
        message.setData(songsMap);
        message.setStatus("success");
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    @GetMapping("/{songId}")
    public ResponseEntity<ResponseMessage<Map<String, SongResponse>>> getSongById(@PathVariable String songId) {
        ResponseMessage<Map<String, SongResponse>> responseMessage = new ResponseMessage<>();
        SongResponse songs = songService.findSongById(songId);
        Map<String, SongResponse> songMap = new HashMap<>();
        songMap.put("song", songs);
        responseMessage.setMessage(null);
        responseMessage.setData(songMap);
        responseMessage.setStatus("success");
        return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
    }

    @PutMapping("/{songId}")
    public ResponseEntity<ResponseMessage<Songs>> editSongById(
            @PathVariable String songId, @RequestBody @Valid SongRequest songRequest ) {
        ResponseMessage<Songs> responseMessage = new ResponseMessage<>();
        Songs songs = songService.updateSongByID(songRequest, songId);
        responseMessage.setMessage("Lagu berhasil diperbarui");
        responseMessage.setData(songs);
        responseMessage.setStatus("success");
        return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
    }

    @DeleteMapping("/{songId}")
    public ResponseEntity<ResponseMessage<String>> deleteSongById(@PathVariable String songId) {
        ResponseMessage<String> responseMessage = new ResponseMessage<>();
        Songs songs = songService.deleteSongByID(songId);
        responseMessage.setMessage("Lagu berhasil dihapus");
        responseMessage.setData(null);
        responseMessage.setStatus("success");
        return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
    }

}