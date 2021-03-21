package com.jared.myRetailapi.service;

import com.fasterxml.jackson.core.JsonProcessingException;

/** @author jaredpowell */
public interface RedSkyService {
  String findNameById(String id) throws JsonProcessingException;
}
