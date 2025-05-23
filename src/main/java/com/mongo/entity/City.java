package com.mongo.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection = "city")
public class City {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(
            name = "_id",
            nullable = false,
            columnDefinition = "ObjectId"
    )
    private String id;

    @Field("city_name")
    @TextIndexed
    private String cityName;

    @Field("pin_code")
    //@Indexed(unique = true)
    @TextIndexed
    private String pinCode;

    @GeoSpatialIndexed
    private Point coordinates;

    private List<ObjectId> municipalCorporationIds;
}
