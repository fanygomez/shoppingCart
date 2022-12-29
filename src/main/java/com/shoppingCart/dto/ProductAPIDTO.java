package com.shoppingCart.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductAPIDTO {
    private Integer id;
    private String title;
    private BigDecimal price;
    private String description;
    private String category;
    private String image;
    private ProductRatingDTO rating;
}
