package com.shoppingCart.controllers;

import com.shoppingCart.dto.OrderDetailReqDTO;
import com.shoppingCart.dto.OrderDetailRespDTO;
import com.shoppingCart.dto.OrderRespDTO;
import com.shoppingCart.dto.PaymentReqDTO;
import com.shoppingCart.exceptions.ErrorException;
import com.shoppingCart.services.IOrderDetailService;
import com.shoppingCart.services.IPaymentService;
import com.shoppingCart.utils.ResponseEntityRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/payment")
@Validated
public class PaymentController {
    @Autowired
    private IPaymentService iPaymentService;
    /**
     * {@code POST /}: Create new Order Detail
     * @param request
     * @return
     */
    @PostMapping
    @PreAuthorize("hasAnyRole('BO_ADMIN','BO_USUARIO')")
    public ResponseEntity<?> save(@Valid @RequestBody PaymentReqDTO request){
        System.out.println("request ->"+request);
        try {
            OrderRespDTO payment = iPaymentService.pay(request);
            return ResponseEntityRest.getResponseEntitySuccess(payment, HttpStatus.CREATED);
        }catch (ErrorException e){
            return ResponseEntityRest.getResponseEntityError(e.getMessage(),HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            System.out.println("ERROR POST"+e);
            return ResponseEntityRest.getResponseEntityError(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
