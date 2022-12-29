package com.shoppingCart.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderOrderDetailDTO {
    @NotBlank(message = "Order ID is required")
    private Long orderId;
}
