package com.mongo.service;

import com.mongo.entity.City;

import java.util.List;

public interface CityService {
    City createCity(City city);

    City updateCity(City city);

    List<City> getAllCities();

    void deleteCityById(String id);
}
