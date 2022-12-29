package com.shoppingCart.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailReqDTO {
    @NotNull(message = "Order ID must not be null")
    private Long orderId;
    @NotEmpty(message = "Items  is required")
    @Valid
    private List<ItemsOrderDetailDTO> items = new ArrayList<>();
}
