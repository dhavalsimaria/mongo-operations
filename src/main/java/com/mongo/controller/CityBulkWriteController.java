package com.mongo.controller;

import com.mongo.entity.CityDTO;
import com.mongo.service.CityBulkWriteService;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Integer> bulkInsert(@RequestBody List<CityDTO> cityDTOs) {
        return ResponseEntity.ok(cityBulkWriteService.bulkInsertCities(cityDTOs));
    }

    @DeleteMapping("/list-of-ids")
    public ResponseEntity<Integer> bulkDelete(@RequestBody List<String> ids) {
        return ResponseEntity.ok(cityBulkWriteService.bulkDeleteCitiesUsingListOfIds(ids));
    }

    @DeleteMapping("/list-of-queries")
    public ResponseEntity<Integer> bulkDeleteUsingListOfQueries(@RequestParam String id, @RequestParam String pinCode, @RequestParam String cityName) {
        return ResponseEntity.ok(cityBulkWriteService.bulkDeleteCitiesUsingListOfQueries(id, pinCode, cityName));
    }

    @PutMapping("/list-of-queries/update-all")
    public ResponseEntity<Integer> bulkUpdateMultipleUsingListOfQueries(@RequestParam String id1, @RequestParam String cityName, @RequestParam String id2) {
        return ResponseEntity.ok(cityBulkWriteService.bulkUpdateMultipleCitiesUsingListOfQueries(id1, cityName, id2));
    }

    @PutMapping("/list-of-queries/update-first")
    public ResponseEntity<Integer> bulkUpdateOneUsingListOfQueries(@RequestParam String id, @RequestParam String cityName, @RequestParam String pinCode) {
        return ResponseEntity.ok(cityBulkWriteService.bulkUpdateOneCityUsingListOfQueries(id, cityName, pinCode));
    }
}
