package com.shoppingCart.dto;
public class OrderDetailDTO {
    private Long productId;

    public OrderDetailDTO() {}
    public OrderDetailDTO(Long productId) {
        this.productId = productId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
