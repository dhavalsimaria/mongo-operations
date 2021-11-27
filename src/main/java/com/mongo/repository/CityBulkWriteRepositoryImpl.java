package com.mongo.repository;

import com.mongo.entity.City;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.util.Pair;
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

    /**
     * Bulk insert operation in 'Ordered' mode, which means method takes a list of cities and inserts them in the database in ordered manner.
     * Method returns the count of cities persisted in database.
     *
     * @param cities  list of cities to be inserted, not null
     * @return count of inserted cities
     */
    @Override
    public Integer bulkInsertCities(List<City> cities) {
        return mongoTemplate.bulkOps(BulkOperations.BulkMode.ORDERED, City.class).insert(cities).execute().getInsertedCount();
    }

    /**
     * Bulk remove operation which removes documents based on a list of document ids.
     * Method returns the count of cities removed from the database.
     *
     * @param ids
     * @return
     */
    @Override
    public Integer bulkRemoveCitiesUsingListOfIds(List<String> ids) {
        Query bulkRemoveQuery = new Query().addCriteria(Criteria.where("_id").in(ids));

        return mongoTemplate.bulkOps(BulkOperations.BulkMode.ORDERED, City.class).remove(bulkRemoveQuery).execute().getDeletedCount();
    }

    /**
     * Bulk remove operation which takes 3 inputs parameters that are used with 3 different queries.
     * All documents that satisfy any of the 3 queries are removed.
     * Method returns the count of cities removed from the database.
     *
     * @param id - To be used with first query
     * @param pinCode - To be used with second query
     * @param cityName - To be used with third query
     * @return count of cities removed
     */
    @Override
    public Integer bulkRemoveCitiesUsingListOfQueries(String id, String pinCode, String cityName) {
        Query bulkRemoveQuery1 = new Query().addCriteria(Criteria.where("_id").in(id));
        Query bulkRemoveQuery2 = new Query().addCriteria(Criteria.where("pinCode").is(pinCode));
        Query bulkRemoveQuery3 = new Query().addCriteria(Criteria.where("cityName").is(cityName));

        List<Query> bulkRemoveQueries = Stream.of(bulkRemoveQuery1, bulkRemoveQuery2, bulkRemoveQuery3).collect(Collectors.toList());

        return mongoTemplate.bulkOps(BulkOperations.BulkMode.ORDERED, City.class).remove(bulkRemoveQueries).execute().getDeletedCount();
    }

    /**
     * Bulk updateMulti operation which takes 3 inputs parameters that are used with 3 different queries.
     * 'All' documents that satisfy any one of the 3 queries are updated.
     * Update for all three queries are mutually exclusive, i.e. a document do not have to satisfy multiple queries
     * Method returns the count of cities updated in database.
     *
     * @param id1 - To be used with first query
     * @param cityName - To be used with second query
     * @param id2 - To be used with third query
     * @returncount of cities updated
     */
    @Override
    public Integer bulkUpdateCitiesUsingUpdateMulti(String id1, String cityName, String id2) {
        Query bulkUpdateQuery1 = new Query().addCriteria(Criteria.where("_id").in(id1));
        Update updateDefinition1 = new Update().set("cityName", "UpdateMulti City Name 1");

        Query bulkUpdateQuery2 = new Query().addCriteria(Criteria.where("cityName").in(cityName));
        Update updateDefinition2 = new Update().set("cityName", "UpdateMulti City Name 2");

        Query bulkUpdateQuery3 = new Query().addCriteria(Criteria.where("_id").in(id2));
        Update updateDefinition3 = new Update().set("pinCode", "101010");

        List<Pair<Query, Update>> updates = Stream.of(Pair.of(bulkUpdateQuery1, updateDefinition1), Pair.of(bulkUpdateQuery2, updateDefinition2), Pair.of(bulkUpdateQuery3, updateDefinition3)).collect(Collectors.toList());

        return mongoTemplate.bulkOps(BulkOperations.BulkMode.ORDERED, City.class).updateMulti(updates).execute().getModifiedCount();
    }

    /**
     * Bulk updateOne operation which takes 3 inputs parameters that are used with 3 different queries.
     * 'First' document that satisfies each query individually will be updated.
     * Update for all three queries are mutually exclusive, i.e. a document do not have to satisfy multiple queries
     * Method returns the count of cities updated in database.
     *
     *
     * @param id - To be used with first query
     * @param cityName - To be used with second query
     * @param pinCode - To be used with third query
     * @return
     */
    @Override
    public Integer bulkUpdateCitiesUsingUpdateOne(String id, String cityName, String pinCode) {
        Query bulkUpdateQuery1 = new Query().addCriteria(Criteria.where("_id").in(id));
        Update updateDefinition1 = new Update().set("cityName", "UpdateOne City Name 1");

        Query bulkUpdateQuery2 = new Query().addCriteria(Criteria.where("cityName").in(cityName));
        Update updateDefinition2 = new Update().set("cityName", "UpdateOne City Name 2");

        Query bulkUpdateQuery3 = new Query().addCriteria(Criteria.where("pinCode").in(pinCode));
        Update updateDefinition3 = new Update().set("pinCode", "101010");

        List<Pair<Query, Update>> updates = Stream.of(Pair.of(bulkUpdateQuery1, updateDefinition1), Pair.of(bulkUpdateQuery2, updateDefinition2), Pair.of(bulkUpdateQuery3, updateDefinition3)).collect(Collectors.toList());

        return mongoTemplate.bulkOps(BulkOperations.BulkMode.ORDERED, City.class).updateOne(updates).execute().getModifiedCount();
    }
}
