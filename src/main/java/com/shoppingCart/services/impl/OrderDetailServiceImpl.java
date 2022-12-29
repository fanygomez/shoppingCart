package com.shoppingCart.services.impl;

import com.shoppingCart.dto.*;
import com.shoppingCart.exceptions.ErrorException;
import com.shoppingCart.models.Order;
import com.shoppingCart.models.OrderDetail;
import com.shoppingCart.models.OrderStatus;
import com.shoppingCart.repositories.OrderDetailRepository;
import com.shoppingCart.services.IOrderDetailService;
import com.shoppingCart.services.IProductService;
import com.shoppingCart.services.IinternalOrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackFor = ErrorException.class)
public class OrderDetailServiceImpl implements IOrderDetailService {
    private final OrderDetailRepository orderDetailRepository;
    private final IinternalOrderService iinternalOrderService;
    private final IProductService iProductService;

    public OrderDetailServiceImpl(OrderDetailRepository orderDetailRepository, IinternalOrderService iinternalOrderService, IProductService iProductService) {
        this.orderDetailRepository = orderDetailRepository;
        this.iinternalOrderService = iinternalOrderService;
        this.iProductService = iProductService;
    }
    @Override
    public OrderDetailRespDTO save(OrderDetailReqDTO request) throws ErrorException {
        try {
            List<OrderDetail> detailList = new ArrayList<>();
            List<BigDecimal> totalAmount = new ArrayList<>();
            BigDecimal total = BigDecimal.valueOf(0);
            Order order = iinternalOrderService.findById(request.getOrderId());

            List<OrderDetail> orderDetails =orderDetailRepository.findByOrderIdId(request.getOrderId());
            if (order.getStatus().equals(OrderStatus.CANCELLED) || !order.getStatus().equals(OrderStatus.PENDING)){
                throw  new ErrorException("This order is cancelled/.");
            }
            /**
             * Update detail
             */
            BigDecimal updateTotal = update(orderDetails, request.getItems());
            System.out.println(updateTotal);
            totalAmount.add(updateTotal);
            total = updateTotal;
            /**
             * Add detail
             */
            List<ItemsOrderDetailDTO> newOrderDetail = getNewItems(orderDetails,request.getItems());
            newOrderDetail.forEach(item -> {
                System.out.println("add" +item.getProductId());
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setOrderId(order);

                ProductAPIDTO productAPIDTO = iProductService.findById(item.getProductId());
                totalAmount.add(iProductService.calculateTotalAmount(item.getQuantity(),productAPIDTO.getPrice()));

                orderDetail.setProductId(item.getProductId());
                orderDetail.setQuantity(item.getQuantity());
                System.out.println(orderDetail);
                detailList.add(orderDetail);
            });
            System.out.println(totalAmount);
            System.out.println(newOrderDetail);
            System.out.println(detailList);
            if(detailList.size() > 0){
                detailList.get(0).getOrderId().setStatus(OrderStatus.PENDING_PAYMENT);
                total = totalAmount.stream().parallel().reduce(BigDecimal.valueOf(0), BigDecimal::add);
                detailList.get(0).getOrderId().setAmount(total);
                System.out.println(detailList);
                orderDetailRepository.saveAll(detailList);
            }
            return new OrderDetailRespDTO(request.getOrderId(), OrderStatus.PENDING_PAYMENT,total);
        }catch (ErrorException e){
            throw  new ErrorException(e.getMessage());
        }
    }

    @Override
    public List<OrderDetail> findByOderId(Long id) throws ErrorException {
        try {
            List<OrderDetail> orderDetails = orderDetailRepository.findByOrderIdId(id);
            if(orderDetails.size() == 0){
                throw new ErrorException("Order doesn't exists.");
            }
            return orderDetails;
        }catch (ErrorException e){
            throw new ErrorException(e.getMessage());
        }
    }

    /***
     *
     * @param orderDetails and items
     * @param items
     * @return
     * @throws ErrorException
     */
    public BigDecimal update(List<OrderDetail> orderDetails,List<ItemsOrderDetailDTO> items) throws ErrorException {
        try {
            List<BigDecimal> totalAmount = new ArrayList<>();
            BigDecimal total = BigDecimal.valueOf(0);
            Map<Long, ItemsOrderDetailDTO> map= items.stream()
                    .collect(Collectors.toMap(ItemsOrderDetailDTO::getProductId, s -> s));

            orderDetails.forEach(orderDetail -> {
                if(map.get(orderDetail.getProductId()) != null){
                    System.out.println("update::ProductId " +orderDetail.getProductId());
                    orderDetail.setQuantity(orderDetail.getQuantity() + map.get(orderDetail.getProductId()).getQuantity());
                    ProductAPIDTO productAPIDTO = iProductService.findById(orderDetail.getProductId());
                    totalAmount.add(iProductService.calculateTotalAmount(orderDetail.getQuantity(),productAPIDTO.getPrice()));
                }
            });
            if (orderDetails.size() > 0){
                orderDetails.get(0).getOrderId().setStatus(OrderStatus.PENDING_PAYMENT);
                total = totalAmount.stream().parallel().reduce(BigDecimal.valueOf(0), BigDecimal::add);
                orderDetails.get(0).getOrderId().setAmount(total);
                orderDetailRepository.saveAll(orderDetails);
            }
            return total;
        }catch (ErrorException e){
            throw new ErrorException(e.getMessage());
        }
    }

    /**
     *
     * @param orderDetailsExist
     * @param items
     * @return
     * @throws ErrorException
     */
    public List<ItemsOrderDetailDTO> getNewItems(List<OrderDetail> orderDetailsExist,List<ItemsOrderDetailDTO> items) throws ErrorException {
        try {
            Map<Long, OrderDetail> map= orderDetailsExist.stream()
                    .collect(Collectors.toMap(OrderDetail::getProductId, s -> s));
            return items.stream()
                    .filter(s -> map.get(s.getProductId()) == null)
                    .collect(Collectors.toList());
        }catch (ErrorException e){
            throw new ErrorException(e.getMessage());
        }
    }
}
