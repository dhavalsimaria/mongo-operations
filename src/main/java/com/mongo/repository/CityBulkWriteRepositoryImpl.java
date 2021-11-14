package com.mongo.repository;

import com.mongo.entity.City;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CityBulkWriteRepositoryImpl implements CityBulkWriteRepository{

    private final MongoTemplate mongoTemplate;

    public CityBulkWriteRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Integer bulkInsertCities(List<City> cities) {
        return mongoTemplate.bulkOps(BulkOperations.BulkMode.ORDERED, City.class).insert(cities).execute().getInsertedCount();
    }
}
