
package com.openmusic.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author Ahmad Irfaan Hibatullah
 * @version $Id: SecurityConfig.java, v 0.1 2021‐11‐06 12.56 Ahmad Irfaan Hibatullah Exp $$
 */

@Configuration
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}