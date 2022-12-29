package com.shoppingCart.controllers;

import com.shoppingCart.dto.OrderReqDTO;
import com.shoppingCart.dto.OrderReqUpdateDTO;
import com.shoppingCart.dto.OrderRespDTO;
import com.shoppingCart.services.IOrderService;
import com.shoppingCart.exceptions.ErrorException;
import com.shoppingCart.utils.ResponseEntityRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/orders")
@Validated
public class OrderController {
    @Autowired
    IOrderService orderService;
    /**
     * {@code POST /}: Create new order
     * @param request
     * @return
     */
    @PreAuthorize("hasAnyRole('BO_ADMIN','BO_USUARIO')")
    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody OrderReqDTO request){
        try {
            OrderRespDTO newOrder = orderService.save(request);
            return ResponseEntityRest.getResponseEntitySuccess(newOrder, HttpStatus.CREATED);
        }catch (ErrorException e){
            return ResponseEntityRest.getResponseEntityError(e.getMessage(),HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            System.out.println("ERROR POST"+e.getMessage());
            return ResponseEntityRest.getResponseEntityError(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * {@code GET /orders/:id}: Get order by id
     * @param id
     * @return {@link ResponseEntity} with status {@code 200 (OK)}
     * or with status {@code 400 (Bad Request)}
     * or with status {@code 500 (Internal Server Error)}
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('BO_ADMIN','BO_USUARIO')")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id){
        try {
            OrderRespDTO order = orderService.findById(Long.valueOf(id));
            return new ResponseEntity<>(order, HttpStatus.OK);
        }catch (ErrorException e){
            return ResponseEntityRest.getResponseEntityError(e.getMessage(),HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return ResponseEntityRest.getResponseEntityError(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /**
     * {@code GET /orders}: Get all orders
     * @return {@link ResponseEntity} with status {@code 200 (OK)}
     * or with status {@code 400 (Bad Request)}
     * or with status {@code 500 (Internal Server Error)}
     */
    @GetMapping()
    @PreAuthorize("hasRole('BO_ADMIN')")
    public ResponseEntity<?> getByAll(){
        try {
            List<OrderRespDTO> orders = orderService.getAll();
            return new ResponseEntity<>(orders, HttpStatus.OK);
        }catch (ErrorException e){
            return ResponseEntityRest.getResponseEntityError(e.getMessage(),HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return ResponseEntityRest.getResponseEntityError(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /**
     * {@code POST /orders/cancel}: Cancel order
     * @return {@link ResponseEntity} with status {@code 200 (OK)}
     * or with status {@code 400 (Bad Request)}
     * or with status {@code 500 (Internal Server Error)}
     */
    @PreAuthorize("hasAnyRole('BO_ADMIN','BO_USUARIO')")
    @PostMapping("/cancel")
    public ResponseEntity<?> cancel(@Valid @RequestBody OrderReqUpdateDTO request){
        try {
            OrderRespDTO order = orderService.cancel(request);
            return new ResponseEntity<>(order, HttpStatus.OK);
        }catch (ErrorException e){
            return ResponseEntityRest.getResponseEntityError(e.getMessage(),HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return ResponseEntityRest.getResponseEntityError(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
