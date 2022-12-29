package com.shoppingCart.services.impl;

import com.shoppingCart.dto.OrderReqUpdateDTO;
import com.shoppingCart.exceptions.ErrorException;
import com.shoppingCart.dto.OrderReqDTO;
import com.shoppingCart.dto.OrderRespDTO;
import com.shoppingCart.models.Order;
import com.shoppingCart.models.OrderDetail;
import com.shoppingCart.models.OrderStatus;
import com.shoppingCart.repositories.OrderRepository;
import com.shoppingCart.services.IOrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
@Transactional(rollbackFor = ErrorException.class)
public class OrderServiceImpl implements IOrderService {
    private OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public OrderRespDTO save(OrderReqDTO request) throws ErrorException {
        try {
            List<Order> orderByUserId = orderRepository.findByUserIdAndStatus(request.getOrder().getUserId(),OrderStatus.PENDING);
            System.out.println(orderByUserId);
            if (orderByUserId.size() > 0) {
                throw  new ErrorException("This user already has an open order.");
            }
            Order order = new Order();
            order.setUserId(request.getOrder().getUserId());
            order.setStatus(OrderStatus.PENDING);
            Order newOrder = this.orderRepository.save(order);
            return new OrderRespDTO(newOrder.getId(), newOrder.getStatus());
        }catch (ErrorException e){
            throw new ErrorException(e.getMessage());
        }
    }

    @Override
    public OrderRespDTO findById(Long id) throws ErrorException{
        try {
            Optional<Order> orderOptional = this.orderRepository.findById(id);
            if(!orderOptional.isPresent()){
                throw new ErrorException("Order doesn't exist.");
            }
            return new OrderRespDTO(orderOptional.get().getId(),orderOptional.get().getStatus());
        }catch (ErrorException e){
            throw new ErrorException(e.getMessage());
        }
    }

    @Override
    public List<OrderRespDTO> getAll() throws ErrorException {
        try {
            List<OrderRespDTO> orderRespDTOS = new ArrayList<>();
            List<Order> orderOptional = this.orderRepository.findAll();
            orderOptional.forEach(order -> {
                orderRespDTOS.add(new OrderRespDTO(order.getId(),order.getStatus()));
            });
            return orderRespDTOS;
        }catch (ErrorException e){
            throw new ErrorException(e.getMessage());
        }
    }

    @Override
    public OrderRespDTO cancel(OrderReqUpdateDTO reqUpdateDTO) throws ErrorException {
        Order order = orderRepository.findByIdAndUserId(reqUpdateDTO.getOrderId(),reqUpdateDTO.getUserId());
        System.out.println(order);
        if (order == null) {
            throw  new ErrorException("Order doesn't exist");
        }
        order.setStatus(OrderStatus.CANCELLED);
        orderRepository.save(order);
        return new OrderRespDTO(order.getId(),order.getStatus());
    }

    public Order findOrderByIdAndUserId(Long orderId, Long userId) throws ErrorException {
        Order order = orderRepository.findByIdAndUserId(orderId,userId);
        System.out.println(order);
        if (order == null) {
            throw  new ErrorException("Order doesn't exist");
        }
        order.setStatus(OrderStatus.CANCELLED);
        orderRepository.save(order);
        return order;
    }
}
