package com.mongo.controller;

import com.mongo.entity.City;
import com.mongo.service.CityMongoTemplateService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mongo-template/city")
public class CityMongoTemplateController {

    private CityMongoTemplateService cityMongoTemplateService;

    public CityMongoTemplateController(CityMongoTemplateService cityMongoTemplateService) {
        this.cityMongoTemplateService = cityMongoTemplateService;
    }

    @GetMapping
    public List<City> getAll() {
        return cityMongoTemplateService.getAllCities();
    }

    @PostMapping("/insert")
    public City createUsingInsert(@RequestBody City city) {
        return cityMongoTemplateService.insertCity(city);
    }

    @PutMapping("/save")
    public City createOrUpdate(@RequestBody City city) {
        return cityMongoTemplateService.saveCity(city);
    }

    @PutMapping("/update-all")
    public Long updateAllAndGetUpdatedCount(@RequestParam String cityOldName, @RequestParam String cityNewName) {
        return cityMongoTemplateService.updateAllCityNamesAndGetUpdatedCount(cityOldName, cityNewName);
    }

    @PutMapping("/update-first")
    public Long updateFirstAndGetUpdatedCount(@RequestParam String cityOldName, @RequestParam String cityNewName) {
        return cityMongoTemplateService.updateFirstCityNameAndGetUpdatedCount(cityOldName, cityNewName);
    }

    @PutMapping("/find-and-modify")
    public City findAndModifyCityName(@RequestParam String cityOldName, @RequestParam String cityNewName) {
        return cityMongoTemplateService.findAndModifyCityName(cityOldName, cityNewName);
    }

    @PutMapping("/upsert")
    public String upsert(@RequestBody City city) {
        return cityMongoTemplateService.upsertCity(city);
    }

    @DeleteMapping("/find-and-remove/{id}")
    public void findAndRemove(@PathVariable("id") String id) {
        cityMongoTemplateService.deleteCityById(id);
    }

    @DeleteMapping("/remove")
    public Long remove(@RequestParam String cityName) {
        return cityMongoTemplateService.deleteAndGetDeleteCount(cityName);
    }

    /* Need to revisit this method */
    @DeleteMapping("/find-and-modify/{id}")
    public City deleteUsingfindAndModify(@PathVariable("id") String id, @RequestBody City city) {
        return cityMongoTemplateService.deleteCityAndGetDeletedCity(id, city);
    }

}
