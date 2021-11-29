package com.mongo.repository;

import com.mongo.entity.City;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.*;
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
        Query query = new Query().addCriteria(Criteria.where("cityName").is(oldCityName));
        Update updateDefinition = new Update().set("cityName", newCityName);

        UpdateResult updateResult = mongoTemplate.updateMulti(query, updateDefinition, City.class);
        return updateResult.getModifiedCount();
    }

    @Override
    public Long updateCityNameUsingUpdateFirst(String oldCityName, String newCityName) {
        Query query = new Query().addCriteria(Criteria.where("cityName").is(oldCityName));
        Update updateDefinition = new Update().set("cityName", newCityName);

        UpdateResult updateResult = mongoTemplate.updateFirst(query, updateDefinition, City.class);
        return updateResult.getModifiedCount();
    }

    @Override
    public City updateCityNameUsingFindAndModify(String oldCityName, String newCityName) {
        Query query = new Query().addCriteria(Criteria.where("cityName").is(oldCityName));
        Update updateDefinition = new Update().set("cityName", newCityName);
        FindAndModifyOptions options = new FindAndModifyOptions().returnNew(true);

        return mongoTemplate.findAndModify(query, updateDefinition, options, City.class);
    }

    @Override
    public City saveCity(City city) {
        return mongoTemplate.save(city);
    }

    @Override
    public String upsertCity(City city) {
        Query query = new Query().addCriteria(Criteria.where("_id").is(city.getId()));
        Update updateDefinition = new Update().set("cityName", city.getCityName());

        UpdateResult updateResult = mongoTemplate.upsert(query, updateDefinition, City.class);
        return updateResult.getUpsertedId().toString();
    }

    @Override
    public List<City> getAllCitiesUsingFindAll() {
        return mongoTemplate.findAll(City.class);
    }

    /**
     * Method to delete documents using 'find & remove' method.
     * This method does not return any value
     *
     * @param id - id value to filter the document to be deleted based on 'id' field
     */
    @Override
    public void deleteCityByIdUsingFindAndRemove(String id) {
        Query deleteQuery = new Query().addCriteria(Criteria.where("_id").is(id));
        mongoTemplate.findAndRemove(deleteQuery, City.class);
    }

    /**
     * Method to delete documents using 'remove' method.
     * Unlike 'find & remove' method, 'remove' method returns the count of deleted documents.
     *
     * @param inputCityName - city name value to filter the document to be deleted based on 'cityName' field
     * @return - count of deleted documents
     */
    @Override
    public Long deleteCityUsingRemove(String inputCityName) {
        Query deleteQuery = new Query().addCriteria(Criteria.where("cityName").is(inputCityName));

        DeleteResult deleteResult = mongoTemplate.remove(deleteQuery, City.class);
        return deleteResult.getDeletedCount();
    }

    /* Need to revisit this method */
    @Override
    public City deleteCityUsingFindAndModify(String id, City city) {
        Query deleteQuery = new Query().addCriteria(Criteria.where("_id").is(id));

        FindAndModifyOptions options = new FindAndModifyOptions().remove(true).returnNew(true);

        Update updateDefinition = new Update();
        updateDefinition.currentDate("12-11-2021");
        //updateDefinition.set("cityName", city.getCityName());
        //updateDefinition.set("pinCode", city.getPinCode());
        return mongoTemplate.findAndModify(deleteQuery, updateDefinition, options, City.class);
    }

    /**
     * This method is used to find all the documents that contain given search text in fields
     * that are indexed with '@TextIndexed' type of index.
     *
     * @param searchText - Text used to search in the TextIndexed fields
     * @return - all documents containing matching search text
     */
    @Override
    public List<City> getCitiesByTextSearch(String searchText) {
        TextCriteria searchCriteria = TextCriteria.forDefaultLanguage().matchingAny(searchText);
        Query textSearchQuery = TextQuery.queryText(searchCriteria);

        return mongoTemplate.find(textSearchQuery, City.class);
    }
}
