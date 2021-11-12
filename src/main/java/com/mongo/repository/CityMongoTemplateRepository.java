package com.mongo.repository;

import com.mongo.entity.City;

import java.util.List;

public interface CityMongoTemplateRepository {
    City createCityUsingTemplate(City city);

    Long updateCityNameUsingTemplate(String oldCityName, String newCityName);

    City updateCityUsingTemplate(City city);

    List<City> getAllCitiesUsingTemplate();

    void deleteCityByIdUsingTemplate(String id);
}
