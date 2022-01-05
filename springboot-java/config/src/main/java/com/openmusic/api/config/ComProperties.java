
package com.openmusic.api.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Ahmad Irfaan Hibatullah
 * @version $Id: ComProperties.java, v 0.1 2022‐01‐05 10.36 Ahmad Irfaan Hibatullah Exp $$
 */

@Configuration
@ConfigurationProperties(prefix = "com")
public class ComProperties {

    private OpenmusicProperties openmusic;

    /**
     * Getter method for property openmusic.
     *
     * @return property value of openmusic
     */
    public OpenmusicProperties getOpenmusic() {
        return openmusic;
    }

    /**
     * Setter method for property openmusic.
     *
     * @param openmusic value to be assigned to property openmusic
     */

    public void setOpenmusic(OpenmusicProperties openmusic) {
        this.openmusic = openmusic;
    }
}