package com.mongo.service;

import com.mongo.entity.City;
import com.mongo.entity.CityDTO;
import com.mongo.repository.CityMongoTemplateRepository;
import com.mongo.utility.CityMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityMongoTemplateServiceImpl implements CityMongoTemplateService{

    private CityMongoTemplateRepository cityMongoTemplateRepository;

    public CityMongoTemplateServiceImpl(CityMongoTemplateRepository cityMongoTemplateRepository) {
        this.cityMongoTemplateRepository = cityMongoTemplateRepository;
    }

    @Override
    public City saveCity(CityDTO cityDTO) {
        return cityMongoTemplateRepository.saveCity(CityMapper.mapCityToCityDTO(cityDTO));
    }

    @Override
    public City insertCity(CityDTO cityDTO) {
        return cityMongoTemplateRepository.insertCity(CityMapper.mapCityToCityDTO(cityDTO));
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
    public City findAndReplaceUsingCityName(String oldCityName, CityDTO newCityDTO) {
        return cityMongoTemplateRepository.updateUsingFindAndReplace(oldCityName, CityMapper.mapCityToCityDTO(newCityDTO));
    }

    @Override
    public String upsertCity(CityDTO cityDTO) {
        return cityMongoTemplateRepository.upsertCity(CityMapper.mapCityToCityDTO(cityDTO));
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
