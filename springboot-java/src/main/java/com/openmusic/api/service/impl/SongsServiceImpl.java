
package com.openmusic.api.service.impl;

import com.openmusic.api.entities.cache.SongsTemp;
import com.openmusic.api.entities.database.Songs;
import com.openmusic.api.exception.EntityNotFoundException;
import com.openmusic.api.models.request.SongRequest;
import com.openmusic.api.repository.jpa.SongsRepository;
import com.openmusic.api.repository.redis.SongTempRepository;
import com.openmusic.api.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Ahmad Irfaan Hibatullah
 * @version $Id: SongsServiceImpl.java, v 0.1 2021‐10‐20 10.27 Ahmad Irfaan Hibatullah Exp $$
 */
@Service
public class SongsServiceImpl implements SongService {

    protected final SongsRepository songsRepository;
    protected final SongTempRepository songTempRepository;

    @Autowired
    public SongsServiceImpl(SongsRepository songsRepository, SongTempRepository songTempRepository) {
        this.songsRepository = songsRepository;
        this.songTempRepository = songTempRepository;
    }

    @Override
    public Songs addSong(SongRequest request) {
        Songs songs = new Songs();
        songs.setDuration(request.getDuration());
        songs.setGenre(request.getGenre());
        songs.setPerformer(request.getPerformer());
        songs.setTitle(request.getTitle());
        songs.setYear(request.getYear());
        songs = songsRepository.save(songs);
        return songs;
    }

    @Override
    public List<Songs> findAllSong() {
        return null;
    }

    @Override
    public Songs findSongById(String songId) {
        Songs entities = new Songs();
        SongsTemp songTemp = songTempRepository.findById(songId).orElse(null);
        if (songTemp != null) {
            entities.setId(songTemp.getId());
            entities.setYear(songTemp.getYear());
            entities.setPerformer(songTemp.getPerformer());
            entities.setTitle(songTemp.getTitle());
            entities.setGenre(songTemp.getGenre());
            entities.setDuration(songTemp.getDuration());
        } else {
            entities = songsRepository.findById(songId).orElse(null);
            if (entities == null || entities.getDeletedDate() != null) {
                throw new EntityNotFoundException("Data lagu tidak ditemukan");
            }
            SongsTemp temp = new SongsTemp();
            temp.setId(entities.getId());
            temp.setYear(entities.getYear());
            temp.setPerformer(entities.getPerformer());
            temp.setTitle(entities.getTitle());
            temp.setGenre(entities.getGenre());
            temp.setDuration(entities.getDuration());
            songTempRepository.save(temp);
        }
        return entities;
    }

    @Override
    public Songs updateSongByID(SongRequest request, String songId) {
        Songs songs = findSongById(songId);
        songs.setDuration(request.getDuration());
        songs.setGenre(request.getGenre());
        songs.setPerformer(request.getPerformer());
        songs.setTitle(request.getTitle());
        songs.setYear(request.getYear());
        songs = songsRepository.save(songs);
        songTempRepository.deleteById(songId);
        return songs;
    }

    @Override
    public Songs deleteSongByID(SongRequest request, String songId) {
        Songs songs = findSongById(songId);
        songs.setDeletedDate(LocalDateTime.now());
        songs = songsRepository.save(songs);
        songTempRepository.deleteById(songId);
        return songs;
    }
}