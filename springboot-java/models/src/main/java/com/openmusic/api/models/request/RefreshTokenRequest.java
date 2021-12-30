package com.openmusic.api.models.request;

import com.alipay.tracer.biz.util.ToStringUtil;

import javax.validation.constraints.NotBlank;

/**
 * @author Ahmad Irfaan Hibatullah
 * @version $Id: RefreshTokenRequest.java, v 0.1 2021‐12‐22 17.32 Ahmad Irfaan Hibatullah Exp $$
 */
public class RefreshTokenRequest {

    @NotBlank
    private String refreshToken;

    /**
     * Getter method for property refreshToken.
     *
     * @return property value of refreshToken
     */
    public String getRefreshToken() {
        return refreshToken;
    }

    /**
     * Setter method for property refreshToken.
     *
     * @param refreshToken value to be assigned to property refreshToken
     */

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    @Override
    public String toString() {
        return ToStringUtil.reflectionToLogStringByFields(this);
    }
}