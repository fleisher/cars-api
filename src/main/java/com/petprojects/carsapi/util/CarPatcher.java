package com.petprojects.carsapi.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.petprojects.carsapi.entities.Car;

public interface CarPatcher {
    Car patch(JsonPatch jsonPatch, Car car) throws JsonProcessingException, JsonPatchException;
}
