package com.openmusic.api.repository.jpa;

import com.openmusic.api.entities.database.Collaborations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Ahmad Irfaan Hibatullah
 * @version $Id: CollaborationsRepository.java, v 0.1 2021‐10‐20 00.14 Ahmad Irfaan Hibatullah Exp $$
 */
@Repository
public interface CollaborationsRepository extends JpaRepository<Collaborations, String> {
    Collaborations findByPlaylistsAndUsers(String playlistsId, String userId);
}