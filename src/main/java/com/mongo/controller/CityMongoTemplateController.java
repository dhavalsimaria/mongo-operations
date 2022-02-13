package com.mongo.controller;

import com.mongo.entity.City;
import com.mongo.entity.CityDTO;
import com.mongo.service.CityMongoTemplateService;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<City>> getAll() {
        return ResponseEntity.ok(cityMongoTemplateService.getAllCities());
    }

    @PostMapping("/insert")
    public ResponseEntity<City> createUsingInsert(@RequestBody CityDTO cityDTO) {
        return ResponseEntity.ok(cityMongoTemplateService.insertCity(cityDTO));
    }

    @PutMapping("/save")
    public ResponseEntity<City> createOrUpdate(@RequestBody CityDTO cityDTO) {
        return ResponseEntity.ok(cityMongoTemplateService.saveCity(cityDTO));
    }

    @PutMapping("/update-all")
    public ResponseEntity<Long> updateAllAndGetUpdatedCount(@RequestParam String cityOldName, @RequestParam String cityNewName) {
        return ResponseEntity.ok(cityMongoTemplateService.updateAllCityNamesAndGetUpdatedCount(cityOldName, cityNewName));
    }

    @PutMapping("/update-first")
    public ResponseEntity<Long> updateFirstAndGetUpdatedCount(@RequestParam String cityOldName, @RequestParam String cityNewName) {
        return ResponseEntity.ok(cityMongoTemplateService.updateFirstCityNameAndGetUpdatedCount(cityOldName, cityNewName));
    }

    @PutMapping("/find-and-modify")
    public ResponseEntity<City> findAndModifyCityName(@RequestParam String cityOldName, @RequestParam String cityNewName) {
        return ResponseEntity.ok(cityMongoTemplateService.findAndModifyCityName(cityOldName, cityNewName));
    }

    @PutMapping("/find-and-replace")
    public ResponseEntity<City> findAndReplaceUsingCityName(@RequestParam String oldCityName, @RequestBody CityDTO newCityDTO) {
        return ResponseEntity.ok(cityMongoTemplateService.findAndReplaceUsingCityName(oldCityName, newCityDTO));
    }

    @PutMapping("/upsert")
    public ResponseEntity<String> upsert(@RequestBody CityDTO cityDTO) {
        return ResponseEntity.ok(cityMongoTemplateService.upsertCity(cityDTO));
    }

    @DeleteMapping("/find-and-remove/{id}")
    public void findAndRemove(@PathVariable String id) {
        cityMongoTemplateService.deleteCityById(id);
    }

    @DeleteMapping("/remove")
    public ResponseEntity<Long> remove(@RequestParam String cityName) {
        return ResponseEntity.ok(cityMongoTemplateService.deleteAndGetDeleteCount(cityName));
    }

    /* Need to revisit this method */
    @DeleteMapping("/find-and-modify/{id}")
    public ResponseEntity<City> deleteUsingfindAndModify(@PathVariable String id, @RequestBody City city) {
        return ResponseEntity.ok(cityMongoTemplateService.deleteCityAndGetDeletedCity(id, city));
    }

    @GetMapping("/text-search/{searchText}")
    public ResponseEntity<List<City>> getBySearchText(@PathVariable String searchText) {
        return ResponseEntity.ok(cityMongoTemplateService.getCitiesByTextSearch(searchText));
    }

}
