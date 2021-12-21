
package com.openmusic.api.repository.jpa;

import com.openmusic.api.entities.database.Authentication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Ahmad Irfaan Hibatullah
 * @version $Id: AuthenticationRepository.java, v 0.1 2021‐10‐19 19.37 Ahmad Irfaan Hibatullah Exp $$
 */

@Repository
public interface AuthenticationRepository extends JpaRepository<Authentication, String> {
    Authentication findByToken(String token);
}