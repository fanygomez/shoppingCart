package com.shoppingCart.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseDTO {
    private List<String> errors;

    public final List<String> getErrors(String error) {
        List<String> errors = new ArrayList<>();
        errors.add(error);
        return errors;
    }
}
