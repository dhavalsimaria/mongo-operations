package com.mongo.service;

import com.mongo.entity.City;
import com.mongo.repository.CityBulkWriteRepositoryImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityBulkWriteServiceImpl implements CityBulkWriteService{

    private CityBulkWriteRepositoryImpl cityBulkWriteRepositoryImpl;

    public CityBulkWriteServiceImpl(CityBulkWriteRepositoryImpl cityBulkWriteRepositoryImpl) {
        this.cityBulkWriteRepositoryImpl = cityBulkWriteRepositoryImpl;
    }

    @Override
    public Integer bulkInsertCities(List<City> cities) {
        return cityBulkWriteRepositoryImpl.bulkInsertCities(cities);
    }

    @Override
    public Integer bulkDeleteCitiesUsingListOfIds(List<String> ids) {
        return cityBulkWriteRepositoryImpl.bulkRemoveCitiesUsingListOfIds(ids);
    }

    @Override
    public Integer bulkDeleteCitiesUsingListOfQueries(String id, String pinCode, String cityName) {
        return cityBulkWriteRepositoryImpl.bulkRemoveCitiesUsingListOfQueries(id, pinCode, cityName);
    }
}
