package com.shoppingCart.services;

import com.shoppingCart.dto.OrderDetailReqDTO;
import com.shoppingCart.dto.OrderDetailRespDTO;
import com.shoppingCart.exceptions.ErrorException;
import com.shoppingCart.models.OrderDetail;

import java.util.List;

public interface IOrderDetailService  extends  GenericService<OrderDetailReqDTO, OrderDetailRespDTO>{
    List<OrderDetail> findByOderId(Long id) throws ErrorException;
}
