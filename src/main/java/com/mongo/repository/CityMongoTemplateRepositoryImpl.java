package com.mongo.repository;

import com.mongo.entity.City;
import com.mongodb.client.result.UpdateResult;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

public class CityMongoTemplateRepositoryImpl implements CityMongoTemplateRepository{

    private final MongoTemplate mongoTemplate;

    public CityMongoTemplateRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public City createCityUsingTemplate(City city) {
        return mongoTemplate.save(city);
    }

    @Override
    public Long updateCityNameUsingTemplate(String oldCityName, String newCityName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("cityName").is(oldCityName));

        Update updateDefinition = new Update();
        updateDefinition.set("cityName", newCityName);
        UpdateResult updateResult = mongoTemplate.updateFirst(query, updateDefinition, City.class);
        return updateResult.getModifiedCount();
    }

    @Override
    public City updateCityUsingTemplate(City city) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(city.getId()));

        /* Handle the condition where city does not already exist,
          as we do not want mongoTemplate to create the document in that scenario
         */
        if(!mongoTemplate.exists(query, City.class)) {
            return null;
        }
        return mongoTemplate.save(city);
    }

    @Override
    public List<City> getAllCitiesUsingTemplate() {
        return mongoTemplate.findAll(City.class);
    }

    @Override
    public void deleteCityByIdUsingTemplate(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));

        mongoTemplate.findAndRemove(query, City.class);
    }
}
