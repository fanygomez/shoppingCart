package com.shoppingCart.models;

import com.fasterxml.jackson.annotation.JsonValue;

public enum OrderStatus {
    PENDING("PENDING"),
    PENDING_PAYMENT("PENDING PAYMENT"),
    ORDER_PAID("ORDER PAID"),
    CANCELLED("CANCELLED"),
    DELIVERY_PENDING("DELIVERY PENDING"),
    DELIVERED("DELIVERED");

    private final String status;
    OrderStatus(String status) {
        this.status = status;
    }

    @JsonValue
    public String getStatus() {
        return this.status;
    }

    public static OrderStatus findByName(String name) {
        OrderStatus result = null;
        for (OrderStatus status : values()) {
            if (status.name().equalsIgnoreCase(name)) {
                result = status;
                break;
            }
        }
        return result;
    }
}
