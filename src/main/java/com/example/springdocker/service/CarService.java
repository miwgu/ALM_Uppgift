package com.example.springdocker.service;

import com.example.springdocker.model.Car;
import com.example.springdocker.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CarService {
    private final CarRepository repository;

    public List<Car> getCars() {
        return repository.findAll();
    }

    public void saveNewCar(Car car) {
        repository.save(car);
    }

    public List<String> getUsableCars() {
        // hämtar alla cars som vi kan använda
        List<Car> usableCars = repository.findCarByCanIUseIt(true);

        // returnerar endast car namnen i en lista
        return usableCars.stream()
                .map(car -> car.getName())
                .collect(Collectors.toList());
    }
}