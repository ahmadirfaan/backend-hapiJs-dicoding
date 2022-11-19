package com.openmusic.api.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Ahmad Irfaan Hibatullah
 * @version $Id: ApplicationProperties.java, v 0.1 2021‐12‐25 10.56 Ahmad Irfaan Hibatullah Exp $$
 */

@Configuration
@ConfigurationProperties(prefix = "com.openmusic.api")
public class ApplicationProperties {

    private String accessTokenKey;
    private String refreshTokenKey;
    private String accessTokenAge;
    private String refreshTokenAge;

    /**
     * Getter method for property accessTokenKey.
     *
     * @return property value of accessTokenKey
     */
    public String getAccessTokenKey() {
        return accessTokenKey;
    }

    /**
     * Setter method for property accessTokenKey.
     *
     * @param accessTokenKey value to be assigned to property accessTokenKey
     */

    public void setAccessTokenKey(String accessTokenKey) {
        this.accessTokenKey = accessTokenKey;
    }

    /**
     * Getter method for property refreshTokenKey.
     *
     * @return property value of refreshTokenKey
     */
    public String getRefreshTokenKey() {
        return refreshTokenKey;
    }

    /**
     * Setter method for property refreshTokenKey.
     *
     * @param refreshTokenKey value to be assigned to property refreshTokenKey
     */

    public void setRefreshTokenKey(String refreshTokenKey) {
        this.refreshTokenKey = refreshTokenKey;
    }

    /**
     * Getter method for property accessTokenAge.
     *
     * @return property value of accessTokenAge
     */
    public String getAccessTokenAge() {
        return accessTokenAge;
    }

    /**
     * Setter method for property accessTokenAge.
     *
     * @param accessTokenAge value to be assigned to property accessTokenAge
     */

    public void setAccessTokenAge(String accessTokenAge) {
        this.accessTokenAge = accessTokenAge;
    }

    /**
     * Getter method for property refreshTokenAge.
     *
     * @return property value of refreshTokenAge
     */
    public String getRefreshTokenAge() {
        return refreshTokenAge;
    }

    /**
     * Setter method for property refreshTokenAge.
     *
     * @param refreshTokenAge value to be assigned to property refreshTokenAge
     */

    public void setRefreshTokenAge(String refreshTokenAge) {
        this.refreshTokenAge = refreshTokenAge;
    }

    @Override
    public String toString() {
        return "ApplicationProperties{" +
                "accessTokenKey='" + accessTokenKey + '\'' +
                ", refreshTokenKey='" + refreshTokenKey + '\'' +
                ", accessTokenAge='" + accessTokenAge + '\'' +
                ", refreshTokenAge='" + refreshTokenAge + '\'' +
                '}';
    }
}