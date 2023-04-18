package com.petprojects.carsapi.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.petprojects.carsapi.entities.Car;
import org.springframework.stereotype.Component;

@Component
public class CarPatcherImpl implements CarPatcher {

    @Override
    public Car patch(JsonPatch jsonPatch, Car car) throws JsonPatchException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode patched = jsonPatch.apply(mapper.convertValue(car, JsonNode.class));
        return mapper.treeToValue(patched, Car.class);
    }
}
