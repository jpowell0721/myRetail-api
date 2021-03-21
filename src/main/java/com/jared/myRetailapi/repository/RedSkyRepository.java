package com.jared.myRetailapi.repository;

import com.jayway.jsonpath.JsonPath;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

/**
 * @author jaredpowell
 */
@Repository
public class RedSkyRepository {

    private static final String URL = "https://redsky.target.com/v3/pdp/tcin/";
    private static final String PARAMS = "?excludes=taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics&key=candidate";
    private static final String JSON_PATH = "$.product.item.product_description.title";

    /**
     * Method for retrieving external data from redsky.target.com
     *
     * @param id : The productId to search for
     * @return String title of the product matching the productId
     */
    public String getProductTitleById(String id) {
        RestTemplate restTemplate = new RestTemplate();

        String productUrl = URL + id + PARAMS;

        ResponseEntity<String> response = restTemplate.getForEntity(productUrl, String.class, id);
        String title = JsonPath.read(response.getBody(), JSON_PATH);

        return JsonPath.read(response.getBody(), JSON_PATH);
    }
}
