
package com.openmusic.api.service;

import com.openmusic.api.entities.database.PlaylistSong;
import com.openmusic.api.entities.database.Playlists;

import java.util.List;

/**
 * @author Ahmad Irfaan Hibatullah
 * @version $Id: PlaylistService.java, v 0.1 2021‐10‐20 07.58 Ahmad Irfaan Hibatullah Exp $$
 */
public interface PlaylistService {
    Playlists addPlaylist(String name, String owner);
    Playlists deletePlaylistById(String playlistId);
    PlaylistSong addSongToPlaylist(String playlistId, String songId);
    List<PlaylistSong> getSongAtPlaylist(String playlistId);
    Playlists findPlaylistById(String playlistId);
    PlaylistSong deleteSongAtPlaylist(String playlistId, String songId);
    Playlists verifyPlaylistOwner(String playlistId, String owner);
    List<Playlists> getAllPlaylist();
}