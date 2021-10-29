
package com.openmusic.api.entities.database;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @author Ahmad Irfaan Hibatullah
 * @version $Id: PlaylistSong.java, v 0.1 2021‐10‐19 19.09 Ahmad Irfaan Hibatullah Exp $$
 */

@Entity
@Table(name = "playlist_song")
public class PlaylistSong extends AbstractTable<String> {

    @Id
    @GenericGenerator(name = "id_playlistsong", strategy = "uuid")
    @GeneratedValue(generator = "id_playlistsong", strategy = GenerationType.IDENTITY)
    private String id;

    @OneToOne()
    @JoinColumn(name = "playlist_id")
    private Playlists playlist;

    @OneToOne()
    @JoinColumn(name = "song_id")
    private Songs song;

    /**
     * Getter method for property id.
     *
     * @return property value of id
     */
    @Override
    public String getId() {
        return id;
    }

    /**
     * Setter method for property id.
     *
     * @param id value to be assigned to property id
     */

    @Override
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