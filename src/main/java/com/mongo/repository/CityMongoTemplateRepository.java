package com.mongo.repository;

import com.mongo.entity.City;

import java.util.List;

public interface CityMongoTemplateRepository {
    /* Create operations */
    City insertCity(City city);

    /* Update operations */
    Long updateCityNameUsingUpdateMulti(String oldCityName, String newCityName);

    Long updateCityNameUsingUpdateFirst(String oldCityName, String newCityName);

    City updateCityNameUsingFindAndModify(String oldCityName, String newCityName);

    /* Update if exists, else Create */
    City saveCity(City city);

    String upsertCity(City city);

    /* Get operations */
    List<City> getAllCitiesUsingFindAll();

    /* Delete operations */
    void deleteCityByIdUsingFindAndRemove(String id);

    Long deleteCityUsingRemove(String cityName);

    City deleteCityUsingFindAndModify(String id, City city);
}
