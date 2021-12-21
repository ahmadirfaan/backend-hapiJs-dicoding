
package com.openmusic.api.repository.jpa;

import com.openmusic.api.entities.database.Songs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Ahmad Irfaan Hibatullah
 * @version $Id: SongsRepository.java, v 0.1 2021‐10‐20 00.19 Ahmad Irfaan Hibatullah Exp $$
 */
@Repository()
public interface SongsRepository extends JpaRepository<Songs, String> {
}