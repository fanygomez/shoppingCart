package com.shoppingCart.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderReqUpdateDTO {
    @NotNull(message = "Order  is required")
    private Long orderId;
    @NotNull(message = "User ID  is required")
    private Long userId;
}
