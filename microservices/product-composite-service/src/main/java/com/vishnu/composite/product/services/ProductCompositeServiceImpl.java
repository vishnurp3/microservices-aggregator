package com.vishnu.composite.product.services;

import com.vishnu.api.composite.product.*;
import com.vishnu.api.core.product.Product;
import com.vishnu.api.core.recommendation.Recommendation;
import com.vishnu.api.core.review.Review;
import com.vishnu.api.exceptions.NotFoundException;
import com.vishnu.util.http.ServiceUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
public class ProductCompositeServiceImpl implements ProductCompositeService {

    private final ServiceUtil serviceUtil;
    private final ProductCompositeIntegration integration;

    public ProductCompositeServiceImpl(
            ServiceUtil serviceUtil,
            ProductCompositeIntegration integration
    ) {
        this.serviceUtil = serviceUtil;
        this.integration = integration;
    }

    @Override
    public ProductAggregate getProduct(int productId) {
        Product product = integration.getProduct(productId);
        if (product == null) {
            throw new NotFoundException("No product found for productId: " + productId);
        }
        List<Recommendation> recommendations = integration.getRecommendations(productId);
        List<Review> reviews = integration.getReviews(productId);
        return createProductAggregate(product, recommendations, reviews, serviceUtil.serviceAddress());
    }

    private ProductAggregate createProductAggregate(
            Product product,
            List<Recommendation> recommendations,
            List<Review> reviews,
            String serviceAddress
    ) {
        int productId = product.productId();
        String name = product.name();
        int weight = product.weight();
        List<RecommendationSummary> recommendationSummaries =
                (recommendations == null) ? null : recommendations.stream()
                        .map(r -> new RecommendationSummary(r.recommendationId(), r.author(), r.rate()))
                        .collect(Collectors.toList());
        List<ReviewSummary> reviewSummaries =
                (reviews == null) ? null : reviews.stream()
                        .map(r -> new ReviewSummary(r.reviewId(), r.author(), r.subject()))
                        .collect(Collectors.toList());
        String productAddress = product.serviceAddress();
        String reviewAddress = (reviews != null && !reviews.isEmpty()) ? reviews.getFirst().serviceAddress() : "";
        String recommendationAddress = (recommendations != null && !recommendations.isEmpty()) ? recommendations.getFirst().serviceAddress() : "";
        ServiceAddresses serviceAddresses = new ServiceAddresses(serviceAddress, productAddress, reviewAddress, recommendationAddress);
        return new ProductAggregate(productId, name, weight, recommendationSummaries, reviewSummaries, serviceAddresses);
    }
}
