package com.vishnu.api.core.recommendation;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface RecommendationService {

    @GetMapping(
            value = "/recommendation",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    List<Recommendation> getRecommendations(
            @RequestParam(value = "productId", required = true) int productId
    );
}
