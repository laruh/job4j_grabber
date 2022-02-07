package ru.job4j.ood.lsp.parking;

import java.util.ArrayList;
import java.util.List;

public class MemStore implements Store {
    private List<Car> cars = new ArrayList<>();

    @Override
    public void add(Car car) {
        if (!contains(car)) {
            cars.add(car);
        }

    }

    @Override
    public boolean contains(Car car) {
        return cars.contains(car);
    }

    @Override
    public List<Car> findAll() {
        return List.copyOf(cars);
    }
}
