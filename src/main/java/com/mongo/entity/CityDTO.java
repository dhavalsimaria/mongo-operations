package com.mongo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CityDTO {

    private String id;

    private String cityName;

    private String pinCode;

    private Coordinates coordinates;

    private List<ObjectId> municipalCorporationIds;

}
