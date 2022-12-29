package com.shoppingCart.services.impl;

import com.shoppingCart.dto.ProductAPIDTO;
import com.shoppingCart.exceptions.ErrorException;
import com.shoppingCart.services.IProductService;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;

import java.math.BigDecimal;

/**
 * This implementation contains the necessary methods to get the data from the external interface (Products).
 * And calculate total amount
 */
@Service
public class ProductServiceImpl  implements IProductService {
    final String EXTERNAL_SERVICE = "https://fakestoreapi.com";
    @Override
    public ProductAPIDTO[] getAll() throws ErrorException {
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity entity = new HttpEntity<>(headers);
            String url = EXTERNAL_SERVICE + "/products";
            ResponseEntity<ProductAPIDTO[]> response = restTemplate.exchange(url, HttpMethod.GET, entity, ProductAPIDTO[].class, 1);
            // check response
            if (response.getStatusCode() != HttpStatus.OK) {
                throw  new ErrorException("An error occurred while processing the request to the Products API.");
            }
            return response.getBody();
        }catch (ErrorException e){
            throw new ErrorException(e.getMessage());
        }
    }

    @Override
    public ProductAPIDTO findById(Long id) throws ErrorException{
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity entity = new HttpEntity<>(headers);
            String url = EXTERNAL_SERVICE + "/products/"+id;
            ResponseEntity<ProductAPIDTO> response = restTemplate.exchange(url, HttpMethod.GET, entity, ProductAPIDTO.class, 1);
            // check response
            System.out.println("response");
            System.out.println(response.getBody());
            if (response.getStatusCode() != HttpStatus.OK || response.getBody() == null) {
                throw  new ErrorException("The product does not exist.");
            }
            return response.getBody();
        }catch (ErrorException e){
            throw new ErrorException(e.getMessage());
        }
    }

    @Override
    public BigDecimal calculateTotalAmount(Integer quantity, BigDecimal price) throws ErrorException{
        try {
            BigDecimal quantityBig = new BigDecimal(quantity);
            return quantityBig.multiply(price);
        }catch (ErrorException e){
            throw new ErrorException(e.getMessage());
        }
    }

}
