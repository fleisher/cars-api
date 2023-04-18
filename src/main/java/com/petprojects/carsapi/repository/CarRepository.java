package com.petprojects.carsapi.repository;

import com.petprojects.carsapi.entities.Car;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CarRepository extends CrudRepository<Car, Long> {
    Iterable<Car> findByColor(String color);

    Optional<Car> findByRequestUuid (UUID uuid);
}
