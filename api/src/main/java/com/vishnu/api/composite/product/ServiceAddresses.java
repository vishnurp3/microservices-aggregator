package com.vishnu.api.composite.product;

public record ServiceAddresses(
        String compositeAddress,
        String productAddress,
        String reviewAddress,
        String recommendationAddress
) {
}
