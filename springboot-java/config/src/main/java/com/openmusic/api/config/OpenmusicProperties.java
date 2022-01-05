
package com.openmusic.api.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Ahmad Irfaan Hibatullah
 * @version $Id: OpenmusicProperties.java, v 0.1 2022‐01‐05 10.36 Ahmad Irfaan Hibatullah Exp $$
 */

@Configuration
@ConfigurationProperties(prefix = "com.openmusic")
public class OpenmusicProperties {

    private ApplicationProperties api;

    /**
     * Getter method for property api.
     *
     * @return property value of api
     */
    public ApplicationProperties getApi() {
        return api;
    }

    /**
     * Setter method for property api.
     *
     * @param api value to be assigned to property api
     */

    public void setApi(ApplicationProperties api) {
        this.api = api;
    }
}