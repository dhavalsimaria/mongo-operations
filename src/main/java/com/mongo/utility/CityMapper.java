package com.mongo.utility;

import com.mongo.entity.City;
import com.mongo.entity.CityDTO;
import com.mongo.entity.MunicipalCorporation;
import org.springframework.data.geo.Point;

import java.util.List;
import java.util.stream.Collectors;

/**
 * We cannot provide a 'Point' type input in API request.
 * Hence we have created a DTO class CityDTO containing 'latitude' and 'Longitude' fields.
 * This class is used to map CityDTO to City.
 */
public class CityMapper {

    public static City mapCityToCityDTO(CityDTO cityDTO) {
        if(cityDTO.getId() == null)
            return new City(null, cityDTO.getCityName(), cityDTO.getPinCode(), new Point(cityDTO.getCoordinates().getLatitude(), cityDTO.getCoordinates().getLongitude()), cityDTO.getMunicipalCorporationIds());
        else
            return new City(cityDTO.getId(), cityDTO.getCityName(), cityDTO.getPinCode(), new Point(cityDTO.getCoordinates().getLatitude(), cityDTO.getCoordinates().getLongitude()), cityDTO.getMunicipalCorporationIds());
    }

    public static List<City> mapCitiesToCityDTOs(List<CityDTO> cityDTOs) {
        return cityDTOs.stream().map(cityDTO -> mapCityToCityDTO(cityDTO)).collect(Collectors.toList());
    }
}
