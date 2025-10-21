package com.vishnu.core.recommendation.services;

import com.vishnu.api.exceptions.InvalidInputException;
import com.vishnu.api.core.recommendation.Recommendation;
import com.vishnu.api.core.recommendation.RecommendationService;
import com.vishnu.util.http.ServiceUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
public class RecommendationServiceImpl implements RecommendationService {

    private final ServiceUtil serviceUtil;

    public RecommendationServiceImpl(ServiceUtil serviceUtil) {
        this.serviceUtil = serviceUtil;
    }

    @Override
    public List<Recommendation> getRecommendations(int productId) {
        if (productId < 1) {
            throw new InvalidInputException("Invalid productId: " + productId);
        }
        if (productId == 113) {
            log.debug("No recommendations found for productId: {}", productId);
            return new ArrayList<>();
        }
        List<Recommendation> list = new ArrayList<>();
        list.add(new Recommendation(productId, 1, "Author 1", 1, "Content 1", serviceUtil.serviceAddress()));
        list.add(new Recommendation(productId, 2, "Author 2", 2, "Content 2", serviceUtil.serviceAddress()));
        list.add(new Recommendation(productId, 3, "Author 3", 3, "Content 3", serviceUtil.serviceAddress()));
        log.debug("/recommendation response size: {}", list.size());
        return list;
    }
}
