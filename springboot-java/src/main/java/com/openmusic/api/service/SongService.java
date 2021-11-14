
package com.openmusic.api.service;

import com.openmusic.api.entities.database.Songs;
import com.openmusic.api.models.request.SongRequest;
import com.openmusic.api.models.response.GetAllSongResponse;
import com.openmusic.api.models.response.SongResponse;

import java.util.List;

/**
 * @author Ahmad Irfaan Hibatullah
 * @version $Id: SongService.java, v 0.1 2021‐10‐20 08.02 Ahmad Irfaan Hibatullah Exp $$
 */
public interface SongService {
    Songs addSong(SongRequest request);
    List<GetAllSongResponse> findAllSong();
    SongResponse findSongById(String songId);
    Songs updateSongByID(SongRequest request,String songId);
    Songs deleteSongByID(String songId);

}