package com.mongo.repository;

import com.mongo.entity.City;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CityMongoTemplateRepositoryImpl implements CityMongoTemplateRepository{

    private final MongoTemplate mongoTemplate;

    public CityMongoTemplateRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public City insertCity(City city) {
        return mongoTemplate.insert(city);
    }

    @Override
    public Long updateCityNameUsingUpdateMulti(String oldCityName, String newCityName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("cityName").is(oldCityName));

        Update updateDefinition = new Update();
        updateDefinition.set("cityName", newCityName);
        UpdateResult updateResult = mongoTemplate.updateMulti(query, updateDefinition, City.class);
        return updateResult.getModifiedCount();
    }

    @Override
    public Long updateCityNameUsingUpdateFirst(String oldCityName, String newCityName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("cityName").is(oldCityName));

        Update updateDefinition = new Update();
        updateDefinition.set("cityName", newCityName);
        UpdateResult updateResult = mongoTemplate.updateFirst(query, updateDefinition, City.class);
        return updateResult.getModifiedCount();
    }

    @Override
    public City updateCityNameUsingFindAndModify(String oldCityName, String newCityName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("cityName").is(oldCityName));

        Update updateDefinition = new Update();
        updateDefinition.set("cityName", newCityName);

        FindAndModifyOptions options = new FindAndModifyOptions();
        options.returnNew(true);

        return mongoTemplate.findAndModify(query, updateDefinition, options, City.class);
    }

    @Override
    public City saveCity(City city) {
        return mongoTemplate.save(city);
    }

    @Override
    public String upsertCity(City city) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(city.getId()));

        Update updateDefinition = new Update();
        updateDefinition.set("cityName", city.getCityName());
        UpdateResult updateResult = mongoTemplate.upsert(query, updateDefinition, City.class);
        return updateResult.getUpsertedId().toString();
    }

    @Override
    public List<City> getAllCitiesUsingFindAll() {
        return mongoTemplate.findAll(City.class);
    }

    @Override
    public void deleteCityByIdUsingFindAndRemove(String id) {
        Query deleteQuery = new Query();
        deleteQuery.addCriteria(Criteria.where("_id").is(id));

        mongoTemplate.findAndRemove(deleteQuery, City.class);
    }

    @Override
    public Long deleteCityUsingRemove(String inputCityName) {
        Query deleteQuery = new Query();
        deleteQuery.addCriteria(Criteria.where("cityName").is(inputCityName));

        DeleteResult deleteResult = mongoTemplate.remove(deleteQuery, City.class);
        return deleteResult.getDeletedCount();
    }

    /* Need to revisit this method */
    @Override
    public City deleteCityUsingFindAndModify(String id, City city) {
        Query deleteQuery = new Query();
        deleteQuery.addCriteria(Criteria.where("_id").is(id));

        FindAndModifyOptions options = new FindAndModifyOptions();
        options.remove(true);
        options.returnNew(true);

        Update updateDefinition = new Update();
        updateDefinition.currentDate("12-11-2021");
        //updateDefinition.set("cityName", city.getCityName());
        //updateDefinition.set("pinCode", city.getPinCode());
        return mongoTemplate.findAndModify(deleteQuery, updateDefinition, options, City.class);
    }
}
