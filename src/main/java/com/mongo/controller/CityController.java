package com.mongo.controller;

import com.mongo.entity.City;
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
    public ResponseEntity<City> create(@RequestBody City city) {
        return ResponseEntity.ok(cityService.createCity(city));
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
}
