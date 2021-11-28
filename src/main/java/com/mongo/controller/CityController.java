package com.mongo.controller;

import com.mongo.entity.City;
import com.mongo.entity.CityDTO;
import com.mongo.entity.Coordinates;
import com.mongo.service.CityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/city")
public class CityController {

    private CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping
    public ResponseEntity<List<City>> getAll() {
        return ResponseEntity.ok(cityService.getAllCities());
    }

    @PostMapping
    public ResponseEntity<City> create(@RequestBody CityDTO cityDTO) {
        return ResponseEntity.ok(cityService.createCity(cityDTO));
    }

    @PostMapping("/create-multiple")
    public ResponseEntity<List<City>> create(@RequestBody List<City> cities) {
        return ResponseEntity.ok(cityService.createMultipleCities(cities));
    }

    @PutMapping
    public ResponseEntity<City> update(@RequestBody City city) {
        return ResponseEntity.ok(cityService.updateCity(city));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        cityService.deleteCityById(id);
    }

    @DeleteMapping
    public void deleteAll() {
        cityService.deleteAll();
    }

    @GetMapping("/find-locations")
    public ResponseEntity<List<City>> findLocationsWithinPolygon(@RequestBody List<Coordinates> polygonCoordinates) {
        return ResponseEntity.ok(cityService.findByCoordinatesWithin(polygonCoordinates));
    }
}
