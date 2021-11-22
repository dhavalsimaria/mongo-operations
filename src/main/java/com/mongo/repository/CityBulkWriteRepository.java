package com.mongo.repository;

import com.mongo.entity.City;

import java.util.List;

public interface CityBulkWriteRepository {
    /* Bulk insert operations */
    Integer bulkInsertCities(List<City> cities);

    /* Bulk remove operations */
    Integer bulkRemoveCitiesUsingListOfIds(List<String> ids);

    Integer bulkRemoveCitiesUsingListOfQueries(String id, String pinCode, String cityName);

    /* Bulk update operations */
    Integer bulkUpdateCitiesUsingUpdateMulti(String id1, String cityName, String id2);

    Integer bulkUpdateCitiesUsingUpdateOne(String id, String cityName, String pinCode);
}
