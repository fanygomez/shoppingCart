package com.shoppingCart.services;

import com.shoppingCart.exceptions.ErrorException;
import com.shoppingCart.models.Order;

public interface IinternalOrderService {
    boolean updateEntity(Order order) throws ErrorException;
    Order findById(Long id) throws ErrorException;
}
