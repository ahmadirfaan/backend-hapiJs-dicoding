
package com.openmusic.api.repository.jpa;

import com.openmusic.api.entities.database.Playlists;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Ahmad Irfaan Hibatullah
 * @version $Id: PlaylistRepository.java, v 0.1 2021‐10‐20 00.15 Ahmad Irfaan Hibatullah Exp $$
 */
@Repository
public interface PlaylistRepository extends JpaRepository<Playlists, String> {
}