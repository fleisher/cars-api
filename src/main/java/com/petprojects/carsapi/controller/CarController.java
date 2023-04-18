package com.petprojects.carsapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.petprojects.carsapi.dto.CarDto;
import com.petprojects.carsapi.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class CarController {

    private final CarService carService;

    @GetMapping("/cars")
    public List<CarDto> getAllCars() {
        return carService.getAllCars();
    }

    @GetMapping("/cars/{id}")
    public CarDto getCar(@PathVariable Long id) {
        return carService.getCarById(id);
    }

    @GetMapping(value = "/cars", params = {"color"})
    public List<CarDto> getAllCars(@RequestParam String color) {
        return carService.getCarByColor(color);
    }

    @PostMapping("/cars")
    @ResponseStatus(HttpStatus.CREATED)
    public CarDto createCar(@RequestBody CarDto carDto,
                            @RequestHeader("X-Request-Id") UUID requestId) {
        return carService.createCar(carDto, requestId);
    }

    @PutMapping("/cars/{id}")
    public CarDto updateCarById(@PathVariable Long id, @RequestBody CarDto carDto) {
        return carService.updateCarById(id, carDto);
    }

    @PatchMapping("/cars/{id}")
    public CarDto patchCarById(@PathVariable Long id, @RequestBody JsonPatch jsonPatch) throws JsonPatchException, JsonProcessingException {
        return carService.patchCarById(id, jsonPatch);
    }

    @DeleteMapping("/cars/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCarById(@PathVariable Long id) {
        carService.deleteCarById(id);
    }
}
