package com.mongo.repository;

import com.mongo.entity.City;

import java.util.List;

public interface CityBulkWriteRepository {
    /* Bulk insert operations */
    Integer bulkInsertCities(List<City> cities);

    /* Bulk remove operations */
    Integer bulkRemoveCitiesUsingListOfIds(List<String> ids);

    Integer bulkRemoveCitiesUsingListOfQueries(String id, String pinCode, String cityName);
}
