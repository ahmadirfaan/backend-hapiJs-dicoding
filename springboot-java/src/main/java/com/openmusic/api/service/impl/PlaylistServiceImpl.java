package com.openmusic.api.service.impl;

import com.openmusic.api.entities.cache.PlaylistSongTemp;
import com.openmusic.api.entities.cache.PlaylistsTemp;
import com.openmusic.api.entities.database.PlaylistSong;
import com.openmusic.api.entities.database.Playlists;
import com.openmusic.api.entities.database.Songs;
import com.openmusic.api.entities.database.Users;
import com.openmusic.api.exception.EntityNotFoundException;
import com.openmusic.api.exception.UnauthorizedException;
import com.openmusic.api.repository.jpa.PlaylistRepository;
import com.openmusic.api.repository.jpa.PlaylistSongRepository;
import com.openmusic.api.repository.redis.PlaylistSongTempRepository;
import com.openmusic.api.repository.redis.PlaylistTempRepository;
import com.openmusic.api.service.PlaylistService;
import com.openmusic.api.service.SongService;
import com.openmusic.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ahmad Irfaan Hibatullah
 * @version $Id: PlaylistServiceImpl.java, v 0.1 2021‐10‐20 10.26 Ahmad Irfaan Hibatullah Exp $$
 */
@Service
public class PlaylistServiceImpl implements PlaylistService {

    protected final PlaylistRepository playlistRepository;
    protected final PlaylistTempRepository playlistTempRepository;
    protected final UserService userService;
    protected final PlaylistSongRepository playlistSongRepository;
    protected final SongService songService;
    protected final PlaylistSongTempRepository playlistSongTempRepository;

    @Autowired
    public PlaylistServiceImpl(PlaylistRepository playlistRepository, PlaylistTempRepository
            playlistTempRepository, UserService userService,
                               PlaylistSongRepository playlistSongRepository,
                               SongService songService, PlaylistSongTempRepository playlistSongTempRepository) {
        this.playlistRepository = playlistRepository;
        this.playlistTempRepository = playlistTempRepository;
        this.userService = userService;
        this.playlistSongRepository = playlistSongRepository;
        this.songService = songService;
        this.playlistSongTempRepository = playlistSongTempRepository;
    }

    @Override
    public Playlists addPlaylist(String name, String owner) {
        Playlists playlists = new Playlists();
        Users users = userService.findByUserId(owner);
        if (users == null) {
            throw new EntityNotFoundException("No Users");
        }
        playlists.setName(name);
        playlists.setOwner(users);
        playlists = playlistRepository.save(playlists);
        return playlists;
    }

    @Override
    public Playlists deletePlaylistById(String playlistId) {
        Playlists playlists = findPlaylistById(playlistId);
        playlists.setDeletedDate(LocalDateTime.now());
        playlists = playlistRepository.save(playlists);
        PlaylistsTemp playlistsTemp = playlistTempRepository.findById(playlistId).get();
        playlistTempRepository.delete(playlistsTemp);
        return playlists;
    }

    @Override
    public PlaylistSong addSongToPlaylist(String playlistId, String songId) {
        PlaylistSong playlistSong = new PlaylistSong();
        Playlists playlist = findPlaylistById(playlistId);
        Songs songs = songService.findSongById(songId);
        playlistSong.setPlaylist(playlist);
        playlistSong.setSong(songs);
        playlistSong = playlistSongRepository.save(playlistSong);
        List<PlaylistSongTemp> playlistSongTempList =
                playlistSongTempRepository.findPlaylistSongTempsByPlaylistId(playlistId);
        playlistSongTempRepository.deleteAll(playlistSongTempList);
        return playlistSong;
    }

    @Override
    public List<PlaylistSong> getSongAtPlaylist(String playlistId) {
        List<PlaylistSong> playlistSongList = new ArrayList<>();
        List<PlaylistSongTemp> playlistSongTempsByPlaylistId =
                playlistSongTempRepository.findPlaylistSongTempsByPlaylistId(playlistId);
        if (playlistSongTempsByPlaylistId.size() == 0 || playlistSongTempsByPlaylistId == null) {
            playlistSongList = playlistSongRepository.findPlaylistSongsByPlaylistId(playlistId);
            List<PlaylistSongTemp> playlistSongTempList = new ArrayList<>();
            for (PlaylistSong playlist : playlistSongList) {
                PlaylistSongTemp playlistSongTemp = new PlaylistSongTemp();
                playlistSongTemp.setPlaylist(playlist.getPlaylist());
                playlistSongTemp.setSong(playlist.getSong());
                playlistSongTemp.setId(playlist.getId());
                playlistSongTempList.add(playlistSongTemp);
            }
            playlistSongTempRepository.saveAll(playlistSongTempsByPlaylistId);
        } else {
            for (PlaylistSongTemp playlistSongTemp : playlistSongTempsByPlaylistId) {
                PlaylistSong playlistSong = new PlaylistSong();
                playlistSong.setId(playlistSongTemp.getId());
                playlistSong.setPlaylist(playlistSongTemp.getPlaylist());
                playlistSong.setSong(playlistSongTemp.getSong());
                playlistSongList.add(playlistSong);
            }
        }
        return playlistSongList;
    }

    @Override
    public Playlists findPlaylistById(String playlistId) {
        Playlists entities = new Playlists();
        PlaylistsTemp playlistsTemp = playlistTempRepository.findById(playlistId).orElse(null);
        if (playlistsTemp != null) {
            entities.setId(playlistsTemp.getId());
            entities.setName(playlistsTemp.getName());
            entities.setOwner(playlistsTemp.getOwner());
        } else {
            entities = playlistRepository.findById(playlistId).orElse(null);
            if (entities == null || entities.getDeletedDate() != null) {
                throw new EntityNotFoundException("Playlist tidak ditemukan");
            }
            PlaylistsTemp temp = new PlaylistsTemp();
            temp.setId(entities.getId());
            temp.setName(entities.getName());
            temp.setOwner(entities.getOwner());
            playlistTempRepository.save(temp);
        }
        return entities;
    }

    @Override
    public PlaylistSong deleteSongAtPlaylist(String playlistId, String songId) {
        Playlists playlists = findPlaylistById(playlistId);
        Songs songs = songService.findSongById(songId);
        PlaylistSong playlistSong = playlistSongRepository.
                findPlaylistSongsByPlaylistAndSong(playlists, songs);
        if (playlistSong == null) {
            throw new EntityNotFoundException("Not Found Playlist and Song");
        }
        PlaylistSongTemp playlistSongTemp =
                playlistSongTempRepository.findPlaylistSongTempsByPlaylistAndSong(playlists, songs);
        playlistSongTempRepository.delete(playlistSongTemp);
        return null;
    }

    @Override
    public Playlists verifyPlaylistOwner(String playlistId, String owner) {
        Playlists playlists = findPlaylistById(playlistId);
        if (!playlists.getOwner().getId().equals(owner)) {
            throw new UnauthorizedException("Anda tidak berhak mengakses resource ini");
        }
        return playlists;
    }
}