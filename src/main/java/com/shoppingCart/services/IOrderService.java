package com.shoppingCart.services;

import com.shoppingCart.dto.OrderReqDTO;
import com.shoppingCart.dto.OrderReqUpdateDTO;
import com.shoppingCart.dto.OrderRespDTO;
import com.shoppingCart.exceptions.ErrorException;

import java.util.List;

public interface IOrderService extends  GenericService<OrderReqDTO,OrderRespDTO> {
    OrderRespDTO findById(Long id) throws ErrorException;
    List<OrderRespDTO> getAll() throws ErrorException;
    OrderRespDTO cancel(OrderReqUpdateDTO reqUpdateDTO) throws ErrorException;
}
