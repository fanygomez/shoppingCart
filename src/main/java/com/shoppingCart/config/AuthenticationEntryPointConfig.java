package com.shoppingCart.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shoppingCart.dto.ResponseDTO;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@Component
public class AuthenticationEntryPointConfig implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        ResponseDTO responseDTO = new ResponseDTO(new ResponseDTO().getErrors("Unauthorised"));
        response.setContentType("application/json");
        response.setStatus(401);
        OutputStream out = response.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(out, responseDTO);
        out.flush();
    }
}
