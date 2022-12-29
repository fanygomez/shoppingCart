package com.shoppingCart.dto;

import com.shoppingCart.models.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailRespDTO {
    private Long orderId;
    private OrderStatus status;
    private BigDecimal totalAmount;

    public OrderDetailRespDTO(Long orderId, OrderStatus status) {
        this.orderId = orderId;
        this.status = status;
    }
}
