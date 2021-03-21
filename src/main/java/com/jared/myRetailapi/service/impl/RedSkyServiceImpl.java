package com.jared.myRetailapi.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jared.myRetailapi.repository.RedSkyRepository;
import com.jared.myRetailapi.service.RedSkyService;
import org.springframework.stereotype.Service;

/**
 * @author jaredpowell
 */
@Service
public class RedSkyServiceImpl implements RedSkyService {

    //We use RedSkyRepository to access redsky.target.com in order to extract name matching productId
    private RedSkyRepository redSkyRepository;

    public RedSkyServiceImpl(RedSkyRepository redSkyRepository) {
        this.redSkyRepository = redSkyRepository;
    }

    @Override
    public String findNameById(String id) throws JsonProcessingException {
        return redSkyRepository.getProductTitleById(id);
    }
}
