
package com.openmusic.api.repository.redis;

import com.openmusic.api.entities.cache.UsersTemp;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


/**
 * @author Ahmad Irfaan Hibatullah
 * @version $Id: UserTempRepository.java, v 0.1 2021‐10‐20 10.18 Ahmad Irfaan Hibatullah Exp $$
 */

@Repository
public interface UserTempRepository extends CrudRepository<UsersTemp, String> {
}