package com.mongo.service;

import com.mongo.entity.City;

import java.util.List;

public interface CityBulkWriteService {
    Integer bulkInsertCities(List<City> cities);

    Integer bulkDeleteCitiesUsingListOfIds(List<String> ids);

    Integer bulkDeleteCitiesUsingListOfQueries(String id, String pinCode, String cityName);

    Integer bulkUpdateMultipleCitiesUsingListOfQueries(String id1, String cityName, String id2);

    Integer bulkUpdateOneCityUsingListOfQueries(String id, String cityName, String pinCode);
}
