package com.mongo.service;

import com.mongo.entity.City;
import com.mongo.repository.CityMongoTemplateRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityMongoTemplateServiceImpl implements CityMongoTemplateService{

    private CityMongoTemplateRepository cityMongoTemplateRepository;

    public CityMongoTemplateServiceImpl(CityMongoTemplateRepository cityMongoTemplateRepository) {
        this.cityMongoTemplateRepository = cityMongoTemplateRepository;
    }

    @Override
    public City saveCity(City city) {
        return cityMongoTemplateRepository.saveCity(city);
    }

    @Override
    public City insertCity(City city) {
        return cityMongoTemplateRepository.insertCity(city);
    }

    @Override
    public Long updateAllCityNamesAndGetUpdatedCount(String oldCityName, String newCityName) {
        return cityMongoTemplateRepository.updateCityNameUsingUpdateMulti(oldCityName, newCityName);
    }

    @Override
    public Long updateFirstCityNameAndGetUpdatedCount(String oldCityName, String newCityName) {
        return cityMongoTemplateRepository.updateCityNameUsingUpdateFirst(oldCityName, newCityName);
    }

    @Override
    public City findAndModifyCityName(String oldCityName, String newCityName) {
        return cityMongoTemplateRepository.updateCityNameUsingFindAndModify(oldCityName, newCityName);
    }

    @Override
    public String upsertCity(City city) {
        return cityMongoTemplateRepository.upsertCity(city);
    }

    @Override
    public List<City> getAllCities() {
        return cityMongoTemplateRepository.getAllCitiesUsingFindAll();
    }

    @Override
    public void deleteCityById(String id) {
        cityMongoTemplateRepository.deleteCityByIdUsingFindAndRemove(id);
    }

    @Override
    public Long deleteAndGetDeleteCount(String cityName) {
        return cityMongoTemplateRepository.deleteCityUsingRemove(cityName);
    }

    /* Need to revisit this method */
    @Override
    public City deleteCityAndGetDeletedCity(String id, City city) {
        return cityMongoTemplateRepository.deleteCityUsingFindAndModify(id, city);
    }

    @Override
    public List<City> getCitiesByTextSearch(String searchText) {
        return cityMongoTemplateRepository.getCitiesByTextSearch(searchText);
    }
}
