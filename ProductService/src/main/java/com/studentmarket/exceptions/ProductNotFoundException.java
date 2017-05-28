package com.studentmarket.exceptions;

import com.studentmarket.models.Product;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
/**
 * Created by Andreas on 5/26/2017.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ProductNotFoundException(int productId) {
        super("No such product: " + productId);
    }
}




