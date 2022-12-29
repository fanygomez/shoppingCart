package com.shoppingCart.config;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shoppingCart.dto.ResponseDTO;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@Component
public class AccessDeniedHandlerConfig implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        ResponseDTO  responseDTO = new ResponseDTO(new ResponseDTO().getErrors("Access Denied"));
        response.setContentType("application/json");
        response.setStatus(403);
        OutputStream out = response.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(out, responseDTO);
        out.flush();
    }
}