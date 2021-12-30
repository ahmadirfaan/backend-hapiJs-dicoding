package com.openmusic.api.models.converter;

import com.openmusic.api.entities.database.Songs;
import com.openmusic.api.models.response.SongResponse;

/**
 * @author Ahmad Irfaan Hibatullah
 * @version $Id: SongConverter.java, v 0.1 2021‐12‐25 12.14 Ahmad Irfaan Hibatullah Exp $$
 */
public class SongConverter {

    public static Songs convertSongResponseToSong(SongResponse response) {
        Songs songs = new Songs();
        songs.setCreatedDate(response.getInsertedAt());
        songs.setTitle(response.getTitle());
        songs.setGenre(response.getGenre());
        songs.setYear(response.getYear());
        songs.setDuration(response.getDuration());
        songs.setPerformer(response.getPerformer());
        songs.setId(response.getId());
        return songs;
    }
}