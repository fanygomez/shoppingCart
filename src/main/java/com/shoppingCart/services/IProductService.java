package com.shoppingCart.services;

import com.shoppingCart.dto.ProductAPIDTO;
import com.shoppingCart.exceptions.ErrorException;

import java.math.BigDecimal;

public interface IProductService {
    ProductAPIDTO[] getAll() throws ErrorException;
    ProductAPIDTO findById(Long id) throws ErrorException;
    BigDecimal calculateTotalAmount(Integer quantity, BigDecimal price) throws ErrorException;
}
