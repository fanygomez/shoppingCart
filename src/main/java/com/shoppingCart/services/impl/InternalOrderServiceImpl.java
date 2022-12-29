package com.shoppingCart.services.impl;

import com.shoppingCart.exceptions.ErrorException;
import com.shoppingCart.models.Order;
import com.shoppingCart.repositories.OrderRepository;
import com.shoppingCart.services.IinternalOrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;
@Service
@Transactional(rollbackFor = ErrorException.class)
public class InternalOrderServiceImpl implements IinternalOrderService {
    private OrderRepository orderRepository;

    public InternalOrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public boolean updateEntity(Order order) {
        try {
            orderRepository.save(order);
            return  true;
        }catch (ErrorException e){
            throw new ErrorException(e.getMessage());
        }
    }

    @Override
    public Order findById(Long id) throws ErrorException {
        try {
            Optional<Order> orderOptional = this.orderRepository.findById(id);
            if(!orderOptional.isPresent()){
                throw new ErrorException("Order doesn't exists.");
            }
            return orderOptional.get();
        }catch (ErrorException e){
            throw new ErrorException(e.getMessage());
        }
    }
}
