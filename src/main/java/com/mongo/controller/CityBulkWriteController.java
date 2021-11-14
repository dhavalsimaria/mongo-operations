package com.mongo.controller;

import com.mongo.entity.City;
import com.mongo.service.CityBulkWriteService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        return cityBulkWriteService.bulkWriteCities(cities);
    }
}
