package com.shoppingCart.controllers;

import com.shoppingCart.dto.OrderDetailReqDTO;
import com.shoppingCart.dto.OrderDetailRespDTO;
import com.shoppingCart.exceptions.ErrorException;
import com.shoppingCart.services.IOrderDetailService;
import com.shoppingCart.utils.ResponseEntityRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/orders/detail")
@Validated
public class OrderDetailController {
    @Autowired
    IOrderDetailService orderDetailService;
    /**
     * {@code POST /}: Create new Order Detail
     * @param request
     * @return
     */
    @PostMapping
    @PreAuthorize("hasAnyRole('BO_ADMIN','BO_USUARIO')")
    public ResponseEntity<?> save(@Valid @RequestBody OrderDetailReqDTO request){
        System.out.println("request ->"+request);
        try {
            OrderDetailRespDTO newOrderDetail = orderDetailService.save(request);
            return ResponseEntityRest.getResponseEntitySuccess(newOrderDetail, HttpStatus.CREATED);
        }catch (ErrorException e){
            return ResponseEntityRest.getResponseEntityError(e.getMessage(),HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            System.out.println("ERROR POST"+e);
            return ResponseEntityRest.getResponseEntityError(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
