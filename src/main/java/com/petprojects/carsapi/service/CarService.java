package com.petprojects.carsapi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.petprojects.carsapi.dto.CarDto;

import java.util.List;
import java.util.UUID;

public interface CarService {

    List<CarDto> getAllCars();

    CarDto getCarById(Long id);

    List<CarDto> getCarByColor(String color);

    CarDto createCar(CarDto carDto, UUID requestId);

    CarDto updateCarById(Long id, CarDto carDto);

    void deleteCarById(Long id);

    CarDto patchCarById(Long id, JsonPatch jsonPatch) throws JsonPatchException, JsonProcessingException;
}
