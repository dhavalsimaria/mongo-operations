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

    @PutMapping
    public City update(@RequestBody City city) {
        return cityService.updateCity(city);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id) {
        cityService.deleteCityById(id);
    }
}
