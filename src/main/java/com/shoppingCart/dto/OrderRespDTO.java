package com.shoppingCart.dto;

import com.shoppingCart.models.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRespDTO {
    private Long orderId;
    private OrderStatus status;
}
