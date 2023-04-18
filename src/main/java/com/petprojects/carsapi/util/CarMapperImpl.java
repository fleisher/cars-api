package com.petprojects.carsapi.util;

import com.petprojects.carsapi.dto.CarDto;
import com.petprojects.carsapi.entities.Car;
import org.springframework.stereotype.Component;

@Component
public class CarMapperImpl implements CarMapper {
    @Override
    public Car toCar(CarDto carDto) {
        return Car.builder()
                .brand(carDto.getBrand())
                .yearProduction(carDto.getYearProduction())
                .color(carDto.getColor())
                .build();
    }

    @Override
    public CarDto toCarDto(Car car) {
        return CarDto.builder()
                .id(car.getId())
                .brand(car.getBrand())
                .yearProduction(car.getYearProduction())
                .color(car.getColor())
                .build();
    }
}
