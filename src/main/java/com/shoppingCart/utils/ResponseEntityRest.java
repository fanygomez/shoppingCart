package com.shoppingCart.utils;

import com.shoppingCart.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseEntityRest {
    public static ResponseEntity<ResponseDTO> getResponseEntityError(String error, HttpStatus httpStatus) {
        return new ResponseEntity<>(new ResponseDTO(new ResponseDTO().getErrors(error)), httpStatus);
    }
    public static ResponseEntity<Object> getResponseEntitySuccess(Object entity, HttpStatus httpStatus) {
        return new ResponseEntity<>(entity, httpStatus);
    }
    public static ResponseEntity<ResponseDTO> getResponseServerError(String error, HttpStatus httpStatus) {
        return new ResponseEntity<>(new ResponseDTO(new ResponseDTO().getErrors(error)), httpStatus);
    }
}
