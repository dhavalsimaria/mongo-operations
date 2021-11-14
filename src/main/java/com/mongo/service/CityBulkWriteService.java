package com.mongo.service;

import com.mongo.entity.City;

import java.util.List;

public interface CityBulkWriteService {
    Integer bulkInsertCities(List<City> cities);

    Integer bulkDeleteCitiesUsingListOfIds(List<String> ids);

    Integer bulkDeleteCitiesUsingListOfQueries(String id, String pinCode, String cityName);
}
