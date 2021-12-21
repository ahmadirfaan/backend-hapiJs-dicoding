
package com.openmusic.api.service.impl;

import com.openmusic.api.entities.cache.SongsTemp;
import com.openmusic.api.entities.database.Songs;
import com.openmusic.api.exception.PathNotFoundException;
import com.openmusic.api.models.request.SongRequest;
import com.openmusic.api.models.response.GetAllSongResponse;
import com.openmusic.api.models.response.SongResponse;
import com.openmusic.api.repository.jpa.SongsRepository;
import com.openmusic.api.repository.redis.SongTempRepository;
import com.openmusic.api.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<GetAllSongResponse> findAllSong() {
        return songsRepository.findAll()
                .stream()
                .filter(songs -> songs.getDeletedDate() == null)
                .map(songs -> new GetAllSongResponse(songs.getId(), songs.getTitle(), songs.getPerformer()))
                .collect(Collectors.toList());
    }

    @Override
    public SongResponse findSongById(String songId) {
        SongResponse entities = new SongResponse();
        SongsTemp songTemp = songTempRepository.findById(songId).orElse(null);
        if (songTemp != null) {
            entities.setId(songTemp.getId());
            entities.setYear(songTemp.getYear());
            entities.setPerformer(songTemp.getPerformer());
            entities.setTitle(songTemp.getTitle());
            entities.setGenre(songTemp.getGenre());
            entities.setDuration(songTemp.getDuration());
            entities.setInsertedAt(songTemp.getCreatedAt());
            entities.setUpdatedAt(songTemp.getUpdatedAt());
        } else {
            Songs song = songsRepository.findById(songId).orElse(null);
            if (song == null || song.getDeletedDate() != null) {
                throw new PathNotFoundException("Data lagu tidak ditemukan");
            }
            if(song.getUpdatedDate() == null) {
                song.setUpdatedDate(song.getCreatedDate());
            }
            // save song by id to the redis
            SongsTemp temp = new SongsTemp();
            temp.setId(entities.getId());
            temp.setYear(entities.getYear());
            temp.setPerformer(entities.getPerformer());
            temp.setTitle(entities.getTitle());
            temp.setGenre(entities.getGenre());
            temp.setDuration(entities.getDuration());
            temp.setCreatedAt(entities.getInsertedAt());
            songTempRepository.save(temp);

            //set response from repository
            entities.setId(song.getId());
            entities.setYear(song.getYear());
            entities.setPerformer(song.getPerformer());
            entities.setTitle(song.getTitle());
            entities.setGenre(song.getGenre());
            entities.setDuration(song.getDuration());
            entities.setInsertedAt(song.getCreatedDate());
            entities.setUpdatedAt(song.getUpdatedDate());
        }
        return entities;
    }

    @Override
    public Songs updateSongByID(SongRequest request, String songId) {
        SongResponse songById = findSongById(songId);
        Songs songs = new Songs();
        songs.setId(songById.getId());
        songs.setDuration(request.getDuration());
        songs.setGenre(request.getGenre());
        songs.setPerformer(request.getPerformer());
        songs.setTitle(request.getTitle());
        songs.setYear(request.getYear());
        songTempRepository.deleteById(songId);
        songs = songsRepository.save(songs);
        return songs;
    }

    @Override
    public Songs deleteSongByID(String songId) {
        SongResponse songResponse = findSongById(songId);
        Songs songs = new Songs();
        songs.setId(songResponse.getId());
        songs.setDeletedDate(LocalDateTime.now());
        songTempRepository.deleteById(songId);
        songs = songsRepository.save(songs);
        return songs;
    }
}