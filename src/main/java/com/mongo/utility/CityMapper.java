package com.mongo.utility;

import com.mongo.entity.City;
import com.mongo.entity.CityDTO;
import org.springframework.data.geo.Point;

import java.util.List;
import java.util.stream.Collectors;

public class CityMapper {

    public static City mapCityToCityDTO(CityDTO cityDTO) {
        if(cityDTO.getId() == null)
            return new City(null, cityDTO.getCityName(), cityDTO.getPinCode(), new Point(cityDTO.getCoordinates().getLatitude(), cityDTO.getCoordinates().getLongitude()));
        else
            return new City(cityDTO.getId(), cityDTO.getCityName(), cityDTO.getPinCode(), new Point(cityDTO.getCoordinates().getLatitude(), cityDTO.getCoordinates().getLongitude()));
    }

    public static List<City> mapCitiesToCityDTOs(List<CityDTO> cityDTOs) {
        return cityDTOs.stream().map(cityDTO -> mapCityToCityDTO(cityDTO)).collect(Collectors.toList());
    }
}
