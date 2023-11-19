package com.phdljr.todocard.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.phdljr.todocard.exception.type.CustomException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(final HttpServletRequest request, final HttpServletResponse response,
        final AccessDeniedException accessDeniedException) {
        CustomException customException = CustomException.BAD_LOGIN;
        ObjectMapper objectMapper = new ObjectMapper();
        response.setStatus(customException.getStatusCode());
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        try {
            response.getWriter().write(objectMapper.writeValueAsString(customException.toDto()));
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
