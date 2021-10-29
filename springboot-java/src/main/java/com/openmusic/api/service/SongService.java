
package com.openmusic.api.service;

import com.openmusic.api.entities.database.Songs;
import com.openmusic.api.models.request.SongRequest;

import java.util.List;

/**
 * @author Ahmad Irfaan Hibatullah
 * @version $Id: SongService.java, v 0.1 2021‐10‐20 08.02 Ahmad Irfaan Hibatullah Exp $$
 */
public interface SongService {
    Songs addSong(SongRequest request);
    List<Songs> findAllSong();
    Songs findSongById(String songId);
    Songs updateSongByID(SongRequest request,String songId);
    Songs deleteSongByID(SongRequest request,String songId);

}