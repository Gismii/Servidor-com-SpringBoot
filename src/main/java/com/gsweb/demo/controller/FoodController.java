package com.gsweb.demo.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gsweb.demo.food.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("food")
public class FoodController {

    ResourceNotFoundException resourceNotFoundException;

    @Autowired
    private FoodRepository repository;


    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public void saveFood(@RequestBody FoodRequestDTO data) {
        Food food = new Food(data);
        repository.save(food);
    }


    //Salva varios objetos!
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/lista")
    public void saveFoods(@RequestBody List<FoodRequestDTO> dataList) {
        List<Food> foodList = dataList.stream()
                .map(Food::new)
                .collect(Collectors.toList());

        repository.saveAll(foodList);
    }

    //Lista todos os objetos
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/food")
    public List<FoodResponseDTO> getAll() {

        List<FoodResponseDTO> foodList = repository.findAll().stream().map(FoodResponseDTO::new).toList();

        return foodList;
    }

    //busca por Id!
    @GetMapping("/food/{id}")
    public List<FoodResponseDTO> getById(@PathVariable Long id) {

        List<Food> foodList = repository.findAll();

        List<FoodResponseDTO> filteredList = foodList
                .stream()
                .filter(food -> food.getId().equals(id))
                .map(FoodResponseDTO::new)
                .collect(Collectors.toList());

        try {
            if (filteredList.isEmpty()) {
                throw new RuntimeException("There is no id: " + id);
            }
        } catch (RuntimeException e) {

            // Aqui você pode fazer o que quiser com a exceção,
            // como imprimir a mensagem ou lidar com ela de forma diferente.
            System.out.println("Exceção capturada: " + e.getMessage());
        }



        return filteredList;

    }

    //deleta por Id!
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/food/{id}")
    public void deleteFoodById(@PathVariable Long id) {
        Food food = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Food not found with ID: " + id));
        repository.delete(food);

    }
    @Transactional
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/food/all")
    public void deleteAllfood() {

        repository.deleteAll();

    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/food/{id}")
    public void updateFoodById(@PathVariable Long id, @RequestBody FoodRequestDTO data) {
        Food food = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Food not found with ID: " + id));

        // Atualiza os dados do objeto Food com base nos valores fornecidos no corpo da requisição
        food.setTitle(data.title());
        food.setDescription(data.description());
        food.setImage(data.image());
        food.setPrice(data.price());

        repository.save(food);
    }





}


