package com.openmusic.api.repository.jpa;

import com.openmusic.api.entities.database.PlaylistSong;
import com.openmusic.api.entities.database.Playlists;
import com.openmusic.api.entities.database.Songs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Ahmad Irfaan Hibatullah
 * @version $Id: PlaylistSongRepository.java, v 0.1 2021‐10‐20 00.15 Ahmad Irfaan Hibatullah Exp $$
 */
@Repository
public interface PlaylistSongRepository extends JpaRepository<PlaylistSong, String> {
    List<PlaylistSong> findPlaylistSongsByPlaylistId(String playlistId);
    PlaylistSong findPlaylistSongsByPlaylistAndSong(Playlists playlists, Songs songs);
}