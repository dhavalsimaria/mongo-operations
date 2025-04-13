package com.mongo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CityResponseDTO {

    private String id;

    @Field("city_name")
    private String cityName;

    @Field("pin_code")
    private String pinCode;

    @Field("coordinates")
    private Coordinates coordinates;

    private List<MunicipalCorporation> municipalCorporationDetails;

}
