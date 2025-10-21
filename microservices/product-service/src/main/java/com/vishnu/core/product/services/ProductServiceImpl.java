package com.vishnu.core.product.services;

import com.vishnu.api.exceptions.InvalidInputException;
import com.vishnu.api.exceptions.NotFoundException;
import com.vishnu.api.core.product.Product;
import com.vishnu.api.core.product.ProductService;
import com.vishnu.util.http.ServiceUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ServiceUtil serviceUtil;

    public ProductServiceImpl(ServiceUtil serviceUtil) {
        this.serviceUtil = serviceUtil;
    }

    @Override
    public Product getProduct(int productId) {
        log.debug("/product return the found product for productId: {}", productId);
        if (productId < 1) {
            throw new InvalidInputException("Invalid productId: " + productId);
        }
        if (productId == 13) {
            throw new NotFoundException("No product found for productId: " + productId);
        }
        return new Product(productId, "name-" + productId, 123, serviceUtil.serviceAddress());
    }
}
