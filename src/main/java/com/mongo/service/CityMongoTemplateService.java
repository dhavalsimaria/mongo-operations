package com.mongo.service;

import com.mongo.entity.City;
import com.mongo.entity.CityDTO;

import java.util.List;

public interface CityMongoTemplateService {
    City saveCity(CityDTO cityDTO);

    City insertCity(CityDTO cityDTO);

    /*City updateCityUsingSave(City city);*/

    Long updateAllCityNamesAndGetUpdatedCount(String oldCityName, String newCityName);

    Long updateFirstCityNameAndGetUpdatedCount(String oldCityName, String newCityName);

    City findAndModifyCityName(String oldCityName, String newCityName);

    City findAndReplaceUsingCityName(String oldCityName, CityDTO newCityDTOy);

    String upsertCity(CityDTO cityDTO);

    List<City> getAllCities();

    void deleteCityById(String id);

    Long deleteAndGetDeleteCount(String cityName);

    City deleteCityAndGetDeletedCity(String id, City city);

    List<City> getCitiesByTextSearch(String searchText);
}
