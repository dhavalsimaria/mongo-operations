package com.mongo.service;

import com.mongo.entity.City;
import com.mongo.entity.CityDTO;
import com.mongo.entity.Coordinates;
import com.mongo.repository.CityRepository;
import com.mongo.utility.CityMapper;
import org.springframework.data.geo.Point;
import org.springframework.data.geo.Polygon;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityServiceImpl implements CityService{

    private CityRepository cityRepository;

    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public City createCity(CityDTO cityDTO) {
        //City city = new City(null == cityDTO.getId() ? null : cityDTO.getId(), cityDTO.getCityName(), cityDTO.getPinCode(), new Point(cityDTO.getCoordinates().getLatitude(), cityDTO.getCoordinates().getLongitude()));
        City city = CityMapper.mapCityToCityDTO(cityDTO);
        return cityRepository.save(city);
    }

    @Override
    public List<City> createMultipleCities(List<City> cities) {
        return cityRepository.saveAll(cities);
    }

    @Override
    public City updateCity(City city) {
        /** Cover the both the cases where the city exist & does not already exist in database */
        /*
        if(city.getId() != null & cityRepository.existsById(city.getId())) {
            return cityRepository.save(city);
        }*/
        return cityRepository.save(city);
    }

    @Override
    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    @Override
    public void deleteCityById(String id) {
        cityRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        cityRepository.deleteAll();
    }

    @Override
    public List<City> findByCoordinatesWithin(List<Coordinates> polygonCoordinates) {
        List<Point> geoPoints = polygonCoordinates.stream().map(co -> new Point(co.getLatitude(), co.getLongitude())).collect(Collectors.toList());
        return cityRepository.findByCoordinatesWithin(new Polygon(geoPoints));
    }
}
