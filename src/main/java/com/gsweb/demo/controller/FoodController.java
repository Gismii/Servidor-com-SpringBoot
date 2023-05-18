package com.gsweb.demo.controller;

import com.gsweb.demo.food.Food;
import com.gsweb.demo.food.FoodRepository;
import com.gsweb.demo.food.FoodRequestDTO;
import com.gsweb.demo.food.FoodResponseDTO;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("food")
public class FoodController {

    @Autowired
    private FoodRepository repository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public void saveFood(@RequestBody FoodRequestDTO data) {
        Food foodData = new Food( data);
        repository.save(foodData);

        return;

    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping()
    public List<FoodResponseDTO> getAll() {

        List<FoodResponseDTO> foodList = repository.findAll().stream().map(FoodResponseDTO::new).toList();

        return foodList;
    }

    @GetMapping("/food/{id}")
    public List<FoodResponseDTO> getById(@PathVariable Long id) {

        List<Food> foodList = repository.findAll();

        List<FoodResponseDTO> filteredList = foodList
                .stream()
                .filter(food -> food.getId().equals(id))
                .map(FoodResponseDTO::new)
                .collect(Collectors.toList());



        return filteredList;
    }


}