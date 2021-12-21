
package com.openmusic.api.entities.cache;

import com.openmusic.api.entities.database.Playlists;
import com.openmusic.api.entities.database.Songs;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import javax.persistence.Id;

/**
 * @author Ahmad Irfaan Hibatullah
 * @version $Id: PlaylistSongTemp.java, v 0.1 2021‐10‐19 19.09 Ahmad Irfaan Hibatullah Exp $$
 */

@RedisHash(value = "playlist_song", timeToLive = 3600)
public class PlaylistSongTemp{

    @Id
    private String id;

    @Indexed
    private Playlists playlist;

    private Songs song;

    /**
     * Getter method for property id.
     *
     * @return property value of id
     */
    public String getId() {
        return id;
    }

    /**
     * Setter method for property id.
     *
     * @param id value to be assigned to property id
     */

    public void setId(String id) {
        this.id = id;
    }

    /**
     * Getter method for property playlist.
     *
     * @return property value of playlist
     */
    public Playlists getPlaylist() {
        return playlist;
    }

    /**
     * Setter method for property playlist.
     *
     * @param playlist value to be assigned to property playlist
     */

    public void setPlaylist(Playlists playlist) {
        this.playlist = playlist;
    }

    /**
     * Getter method for property song.
     *
     * @return property value of song
     */
    public Songs getSong() {
        return song;
    }

    /**
     * Setter method for property song.
     *
     * @param song value to be assigned to property song
     */

    public void setSong(Songs song) {
        this.song = song;
    }
}