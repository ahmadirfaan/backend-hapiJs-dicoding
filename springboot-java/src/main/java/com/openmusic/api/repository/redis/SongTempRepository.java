/**
 * Dana.id.
 * Copyright (c) 2017‐2021 All Rights Reserved.
 */
package com.openmusic.api.repository.redis;

import com.openmusic.api.entities.cache.SongsTemp;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Ahmad Irfaan Hibatullah
 * @version $Id: SongTempRepository.java, v 0.1 2021‐10‐29 22.42 Ahmad Irfaan Hibatullah Exp $$
 */
@Repository
public interface SongTempRepository extends CrudRepository<SongsTemp, String> {
}