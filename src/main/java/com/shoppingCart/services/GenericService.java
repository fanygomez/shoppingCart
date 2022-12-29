package com.shoppingCart.services;

import com.shoppingCart.exceptions.ErrorException;

public interface GenericService <R,T>{
    T save(R request) throws ErrorException;
}
