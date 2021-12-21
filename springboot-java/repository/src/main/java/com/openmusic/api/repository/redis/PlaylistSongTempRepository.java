
package com.openmusic.api.repository.redis;

import com.openmusic.api.entities.cache.PlaylistSongTemp;
import com.openmusic.api.entities.database.Playlists;
import com.openmusic.api.entities.database.Songs;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Ahmad Irfaan Hibatullah
 * @version $Id: PlaylistSongTempRepository.java, v 0.1 2021‐10‐29 22.53 Ahmad Irfaan Hibatullah Exp $$
 */
@Repository
public interface PlaylistSongTempRepository extends CrudRepository<PlaylistSongTemp, String> {
    List<PlaylistSongTemp> findPlaylistSongTempsByPlaylistId(String playlistId);
    PlaylistSongTemp findPlaylistSongTempsByPlaylistAndSong(Playlists playlists, Songs song);

}