
package com.openmusic.api.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openmusic.api.models.response.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Ahmad Irfaan Hibatullah
 * @version $Id: AuthEntryPointJwt.java, v 0.1 2022‐01‐06 16.44 Ahmad Irfaan Hibatullah Exp $$
 */

@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthEntryPointJwt.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        LOGGER.error("Unauthorized error: {}", authException.getMessage());
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        ResponseMessage<String> responseMessage = new ResponseMessage<>();
        responseMessage.setData(null);
        responseMessage.setStatus("fail");
        responseMessage.setMessage("Anda tidak terautorisasi");
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonStr = objectMapper.writeValueAsString(responseMessage);
        response.getOutputStream().println(jsonStr);
    }

}