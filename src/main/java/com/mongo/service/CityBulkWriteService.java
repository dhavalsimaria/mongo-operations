package com.mongo.service;

import com.mongo.entity.City;

import java.util.List;

public interface CityBulkWriteService {
    Integer bulkWriteCities(List<City> cities);
}
