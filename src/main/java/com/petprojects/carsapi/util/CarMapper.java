package com.petprojects.carsapi.util;

import com.petprojects.carsapi.dto.CarDto;
import com.petprojects.carsapi.entities.Car;

public interface CarMapper {
    Car toCar(CarDto carDto);
    CarDto toCarDto(Car car);
}
