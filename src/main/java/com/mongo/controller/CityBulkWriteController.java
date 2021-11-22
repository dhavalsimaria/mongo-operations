package com.mongo.controller;

import com.mongo.entity.City;
import com.mongo.service.CityBulkWriteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bulk-write/city")
public class CityBulkWriteController {

    private CityBulkWriteService cityBulkWriteService;

    public CityBulkWriteController(CityBulkWriteService cityBulkWriteService) {
        this.cityBulkWriteService = cityBulkWriteService;
    }

    @PostMapping
    public Integer bulkInsert(@RequestBody List<City> cities) {
        return cityBulkWriteService.bulkInsertCities(cities);
    }

    @DeleteMapping("/list-of-ids")
    public Integer bulkDelete(@RequestBody List<String> ids) {
        return cityBulkWriteService.bulkDeleteCitiesUsingListOfIds(ids);
    }

    @DeleteMapping("/list-of-queries")
    public Integer bulkDeleteUsingListOfQueries(@RequestParam String id, @RequestParam String pinCode, @RequestParam String cityName) {
        return cityBulkWriteService.bulkDeleteCitiesUsingListOfQueries(id, pinCode, cityName);
    }

    @PutMapping("/list-of-queries/update-all")
    public Integer bulkUpdateMultipleUsingListOfQueries(@RequestParam String id1, @RequestParam String cityName, @RequestParam String id2) {
        return cityBulkWriteService.bulkUpdateMultipleCitiesUsingListOfQueries(id1, cityName, id2);
    }

    @PutMapping("/list-of-queries/update-first")
    public Integer bulkUpdateOneUsingListOfQueries(@RequestParam String id, @RequestParam String cityName, @RequestParam String pinCode) {
        return cityBulkWriteService.bulkUpdateOneCityUsingListOfQueries(id, cityName, pinCode);
    }
}
