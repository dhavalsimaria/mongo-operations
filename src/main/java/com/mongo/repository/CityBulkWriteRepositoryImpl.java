package com.mongo.repository;

import com.mongo.entity.City;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    @Override
    public Integer bulkRemoveCitiesUsingListOfIds(List<String> ids) {
        Query bulkRemoveQuery = new Query();
        bulkRemoveQuery.addCriteria(Criteria.where("_id").in(ids));

        return mongoTemplate.bulkOps(BulkOperations.BulkMode.ORDERED, City.class).remove(bulkRemoveQuery).execute().getDeletedCount();
    }

    @Override
    public Integer bulkRemoveCitiesUsingListOfQueries(String id, String pinCode, String cityName) {


        Query bulkRemoveQuery1 = new Query();
        bulkRemoveQuery1.addCriteria(Criteria.where("_id").in(id));

        Query bulkRemoveQuery2 = new Query();
        bulkRemoveQuery2.addCriteria(Criteria.where("pinCode").is(pinCode));

        Query bulkRemoveQuery3 = new Query();
        bulkRemoveQuery3.addCriteria(Criteria.where("cityName").is(cityName));
        List<Query> bulkRemoveQueries = Stream.of(bulkRemoveQuery1, bulkRemoveQuery2, bulkRemoveQuery3).collect(Collectors.toList());

        return mongoTemplate.bulkOps(BulkOperations.BulkMode.ORDERED, City.class).remove(bulkRemoveQueries).execute().getDeletedCount();
    }
}
