package com.shoppingCart.services.impl;

import com.shoppingCart.dto.OrderRespDTO;
import com.shoppingCart.dto.PaymentReqDTO;
import com.shoppingCart.models.Order;
import com.shoppingCart.models.OrderStatus;
import com.shoppingCart.services.IPaymentService;
import com.shoppingCart.utils.CardData;
import org.springframework.stereotype.Service;

@Service
public class CreditCardServiceImpl implements IPaymentService {
    private  final InternalOrderServiceImpl orderService;

    public CreditCardServiceImpl(InternalOrderServiceImpl orderService) {
        this.orderService = orderService;
    }

    @Override
    public OrderRespDTO pay(PaymentReqDTO reqDTO) {
        Order order = orderService.findById(reqDTO.getOrderId());
        CardData.findCard(new CardData(reqDTO.getFullName(), reqDTO.getAccount(), reqDTO.getSecurityCode()));
         order.setStatus(OrderStatus.ORDER_PAID);
         orderService.updateEntity(order);
         return new OrderRespDTO(order.getId(),order.getStatus());
    }
}
