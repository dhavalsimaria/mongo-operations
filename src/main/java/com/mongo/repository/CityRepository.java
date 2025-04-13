package com.mongo.repository;

import com.mongo.entity.City;
import com.mongo.entity.CityResponseDTO;
import org.springframework.data.geo.Polygon;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends MongoRepository<City, String> {

    List<City> findByCoordinatesWithin(Polygon polygon);

    @Aggregation(pipeline = {
            "{ $lookup: { from: 'municipal_corporation', localField: 'municipalCorporationIds', " +
                    "foreignField: '_id', as: 'municipalCorporationDetails' } }"
    })
    List<CityResponseDTO> findCityWithMunicipalCorporationDetails();
}
