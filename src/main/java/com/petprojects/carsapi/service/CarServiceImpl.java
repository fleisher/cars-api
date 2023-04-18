package com.petprojects.carsapi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.petprojects.carsapi.dto.CarDto;
import com.petprojects.carsapi.entities.Car;
import com.petprojects.carsapi.repository.CarRepository;
import com.petprojects.carsapi.util.CarMapper;
import com.petprojects.carsapi.util.CarPatcher;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RequiredArgsConstructor
@Service
public class CarServiceImpl implements CarService{

    private final CarRepository carRepository;

    private final CarMapper carMapper;

    private final CarPatcher carPatcher;

    public List<CarDto> getAllCars() {
        return StreamSupport.stream(carRepository.findAll().spliterator(), false)
                .map(carMapper::toCarDto)
                .collect(Collectors.toList());
    }

    public CarDto getCarById(Long id) {
        return carRepository.findById(id)
                .map(carMapper::toCarDto)
                .orElseThrow(() -> new EntityNotFoundException("Car not found with id: " + id));
    }

    public List<CarDto> getCarByColor(String color) {
        return StreamSupport.stream(carRepository.findByColor(color).spliterator(), false)
                .map(carMapper::toCarDto)
                .collect(Collectors.toList());
    }

    public CarDto createCar(CarDto carDto, UUID requestId) {

        return carRepository.findByRequestUuid(requestId)
                .map(carMapper::toCarDto)
                .orElseGet(() -> {
                    Car car = carMapper.toCar(carDto);
                    car.setRequestUuid(requestId);
                    return carMapper.toCarDto(carRepository.save(car));
                });
    }

    public CarDto updateCarById(Long id, CarDto carDto) {
        return carRepository.findById(id)
                .map(car -> {
                    car.setBrand(carDto.getBrand());
                    car.setColor(carDto.getColor());
                    car.setYearProduction(carDto.getYearProduction());
                    return carMapper.toCarDto(carRepository.save(car));
                })
                .orElseThrow(() -> new EntityNotFoundException("Car not found with id: " + id));
    }

    public CarDto patchCarById(Long id, JsonPatch jsonPatch) throws JsonPatchException, JsonProcessingException {
        Car car = carRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Car not found with id: " + id));
        return carMapper.toCarDto(carRepository.save(carPatcher.patch(jsonPatch, car)));
    }

    public void deleteCarById(Long id) {
        carRepository.deleteById(id);
    }
}
