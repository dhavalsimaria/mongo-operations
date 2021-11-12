package com.mongo.service;

import com.mongo.entity.City;
import com.mongo.repository.CityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService{

    private CityRepository cityRepository;

    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public City createCity(City city) {
        return cityRepository.save(city);
    }

    @Override
    public City updateCity(City city) {
        /** Cover the both the cases where the city exist & does not already exist in database */
        /*
        if(city.getId() != null & cityRepository.existsById(city.getId())) {
            return cityRepository.save(city);
        }*/
        return cityRepository.save(city);
    }

    @Override
    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    @Override
    public void deleteCityById(String id) {
        cityRepository.deleteById(id);
    }
}
