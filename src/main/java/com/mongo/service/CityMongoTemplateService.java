package com.mongo.service;

import com.mongo.entity.City;

import java.util.List;

public interface CityMongoTemplateService {
    City saveCity(City city);

    City insertCity(City city);

    /*City updateCityUsingSave(City city);*/

    Long updateAllCityNamesAndGetUpdatedCount(String oldCityName, String newCityName);

    Long updateFirstCityNameAndGetUpdatedCount(String oldCityName, String newCityName);

    City findAndModifyCityName(String oldCityName, String newCityName);

    String upsertCity(City city);

    List<City> getAllCities();

    void deleteCityById(String id);

    Long deleteAndGetDeleteCount(String cityName);

    City deleteCityAndGetDeletedCity(String id, City city);

    List<City> getCitiesByTextSearch(String searchText);
}
