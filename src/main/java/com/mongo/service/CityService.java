package com.mongo.service;

import com.mongo.entity.City;
import com.mongo.entity.CityDTO;
import com.mongo.entity.Coordinates;

import java.util.List;

public interface CityService {
    City createCity(CityDTO cityDTO);

    List<City> createMultipleCities(List<CityDTO> cityDTOs);

    City updateCity(City city);

    List<City> getAllCities();

    void deleteCityById(String id);

    void deleteAll();

    List<City> findByCoordinatesWithin(List<Coordinates> polygonCoordinates);
}
