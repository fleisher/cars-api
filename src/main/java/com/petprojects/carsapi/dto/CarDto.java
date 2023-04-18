package com.petprojects.carsapi.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CarDto {

    private Long id;

    private String brand;

    private Long yearProduction;

    private String color;
}
