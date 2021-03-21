package com.jared.myRetailapi.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @author jaredpowell
 */
@Repository
public class RedSkyRepository {

    private static final String URL = "https://redsky.target.com/v3/pdp/tcin/";
    private static final String PARAMS = "?excludes=taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics&key=candidate";

    /**
     * Method for retrieving external data from redsky.target.com
     *
     * @param id : The productId to search for
     * @return String title of the product matching the productId
     * @throws JsonProcessingException
     */
    public String getProductTitleById(String id) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();

        ObjectMapper mapper = new ObjectMapper();

        String productUrl = URL + id + PARAMS;

        ResponseEntity<String> response = restTemplate.getForEntity(productUrl, String.class, id);
        Map<String, Map> info = mapper.readValue(response.getBody(), Map.class);
        Map<String, Map> productMap = info.get("product");
        Map<String, Map> itemMap = productMap.get("item");
        Map<String, String> prodDescrMap = itemMap.get("product_description");
        String title = prodDescrMap.get("title");

        return title;
    }
}
