
package com.openmusic.api.service.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author Ahmad Irfaan Hibatullah
 * @version $Id: JwtTokenUtil.java, v 0.1 2022‐01‐04 14.00 Ahmad Irfaan Hibatullah Exp $$
 */
@Component
public class JwtTokenUtil implements Serializable {

    private static final long serialVersionUID = -1511938339927886399L;

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenUtil.class);

    @Value("${com.openmusic.api.access-token-age}")
    private String accessToken;

    @Value("${com.openmusic.api.refresh-token-age}")
    private String refreshToken;

    @Value("${com.openmusic.api.access-token-age}")
    private Long accessTokenAge;

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${com.openmusic.api.refresh-token-age}")
    private Long refreshTokenAge;

    @PostConstruct
    public void postConstruct() {
        LOGGER.info("ACCESS TOKEN KEY = " + accessToken);
        LOGGER.info("REFRESH TOKEN KEY = " + refreshToken);
        LOGGER.info("ACCESS TOKEN AGE = " + accessTokenAge);
        LOGGER.info("REFRESH TOKEN AGE = " + refreshTokenAge);
        LOGGER.info("Application Name = " + applicationName);
    }


    public String getUserNameFromAccessToken(String token) {
        return getClaimFromAccessToken(token, Claims::getSubject);
    }

    public Date getExpirationDateFromAccessToken(String token) {
        return getClaimFromAccessToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromAccessToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromAccessToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromAccessToken(String token) {
        return Jwts.parser().setSigningKey(accessToken).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpiredAccessToken(String token) {
        final Date expiration = getExpirationDateFromAccessToken(token);
        return expiration.before(new Date());
    }

    public String generateAccessToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateAccessToken(claims, userDetails.getUsername());
    }

    private String doGenerateAccessToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + accessTokenAge * 1000))
                .signWith(SignatureAlgorithm.HS512, accessToken).compact();
    }


    public Boolean validateAccessToken(String token, UserDetails userDetails) {
        final String username = getUserNameFromAccessToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpiredAccessToken(token));
    }

    public String generateRefreshToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateRefreshToken(claims, userDetails.getUsername());
    }

    private String doGenerateRefreshToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + refreshTokenAge * 1000))
                .signWith(SignatureAlgorithm.HS512, refreshToken).compact();
    }


    public Boolean validateRefreshToken(String token, UserDetails userDetails) {
        final String username = getUserNameFromRefreshToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpiredRefreshToken(token));
    }

    public String getUserNameFromRefreshToken(String token) {
        return getClaimFromRefreshToken(token, Claims::getSubject);
    }

    public <T> T getClaimFromRefreshToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromRefreshToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromRefreshToken(String token) {
        return Jwts.parser().setSigningKey(refreshToken).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpiredRefreshToken(String token) {
        final Date expiration = getExpirationDateFromRefreshToken(token);
        return expiration.before(new Date());
    }

    public Date getExpirationDateFromRefreshToken(String token) {
        return getClaimFromRefreshToken(token, Claims::getExpiration);
    }

}