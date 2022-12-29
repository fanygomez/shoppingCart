package com.shoppingCart.services;

import com.shoppingCart.dto.PaymentReqDTO;
import com.shoppingCart.dto.OrderRespDTO;

public interface IPaymentService {
    OrderRespDTO pay(PaymentReqDTO reqDTO);
}
