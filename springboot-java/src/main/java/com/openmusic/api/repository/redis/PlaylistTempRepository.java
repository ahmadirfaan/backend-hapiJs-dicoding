
package com.openmusic.api.repository.redis;



import com.openmusic.api.entities.cache.PlaylistsTemp;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Ahmad Irfaan Hibatullah
 * @version $Id: PlaylistTempRepository.java, v 0.1 2021‐10‐20 10.19 Ahmad Irfaan Hibatullah Exp $$
 */
@Repository
public interface PlaylistTempRepository extends CrudRepository<PlaylistsTemp, String> {
}