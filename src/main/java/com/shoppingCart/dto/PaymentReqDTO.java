package com.shoppingCart.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentReqDTO {
    @NotBlank
    private String fullName;
    @NotBlank
    private String account;
    @NotBlank
    private String securityCode;
    @NotNull
    private Long orderId;
}
