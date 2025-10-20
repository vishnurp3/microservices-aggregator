package com.vishnu.api.core.product;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface ProductService {

    @GetMapping(
            value = "/product/{productId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    Product getProduct(@PathVariable int productId);
}
