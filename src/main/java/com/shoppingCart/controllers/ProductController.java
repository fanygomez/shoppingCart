package com.shoppingCart.controllers;

import com.shoppingCart.dto.OrderRespDTO;
import com.shoppingCart.dto.ProductAPIDTO;
import com.shoppingCart.exceptions.ErrorException;
import com.shoppingCart.services.IProductService;
import com.shoppingCart.utils.ResponseEntityRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("/products")
@Validated
public class ProductController {
    @Autowired
    IProductService productService;
    /**
     * {@code GET /products/:id}: Get products by id
     * @param id
     * @return {@link ResponseEntity} with status {@code 200 (OK)}
     * or with status {@code 400 (Bad Request)}
     * or with status {@code 500 (Internal Server Error)}
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@Valid @PathVariable("id") Integer id){
        try {
            ProductAPIDTO order = productService.findById(Long.valueOf(id));
            return new ResponseEntity<>(order, HttpStatus.OK);
        }catch (ErrorException e){
            return ResponseEntityRest.getResponseEntityError(e.getMessage(),HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return ResponseEntityRest.getResponseEntityError(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * {@code GET /products}: Get all products
     * @return {@link ResponseEntity} with status {@code 200 (OK)}
     * or with status {@code 400 (Bad Request)}
     * or with status {@code 500 (Internal Server Error)}
     */
    @GetMapping()
    @PreAuthorize("hasAnyRole('BO_ADMIN','BO_USUARIO')")
    public ResponseEntity<?> getAll(){
        try {
            ProductAPIDTO[] orders = productService.getAll();
            System.out.println(orders);
            return new ResponseEntity<>(orders, HttpStatus.OK);
        }catch (ErrorException e){
            return ResponseEntityRest.getResponseEntityError(e.getMessage(),HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return ResponseEntityRest.getResponseEntityError(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
