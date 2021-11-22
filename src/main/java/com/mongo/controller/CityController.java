package com.mongo.controller;

import com.mongo.entity.City;
import com.mongo.service.CityService;
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
    public List<City> getAll() {
        return cityService.getAllCities();
    }

    @PostMapping
    public City create(@RequestBody City city) {
        return cityService.createCity(city);
    }

    @PostMapping("/create-multiple")
    public List<City> create(@RequestBody List<City> cities) {
        return cityService.createMultipleCities(cities);
    }

    @PutMapping
    public City update(@RequestBody City city) {
        return cityService.updateCity(city);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        cityService.deleteCityById(id);
    }
}
