package com.sdacodecoolproject.usersmanager.security.token;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sdacodecoolproject.usersmanager.constant.SecurityConstant;
import com.sdacodecoolproject.usersmanager.model.HttpResponse;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;

import java.io.IOException;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public class AuthenticationForbiddenMessage extends Http403ForbiddenEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
            throws IOException {
        HttpResponse httpResponse =
                new HttpResponse(HttpStatus.FORBIDDEN.value(),
                        HttpStatus.FORBIDDEN,
                        HttpStatus.FORBIDDEN.getReasonPhrase(),
                        SecurityConstant.FORBIDDEN);
        response.setContentType(APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.FORBIDDEN.value());
        ServletOutputStream outputStream = response.getOutputStream();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(outputStream, httpResponse);
        outputStream.flush();
    }

}
