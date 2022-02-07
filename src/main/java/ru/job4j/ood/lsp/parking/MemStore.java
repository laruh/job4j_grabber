package ru.job4j.ood.lsp.parking;

import java.util.ArrayList;
import java.util.List;

public class MemStore implements Store {
    private List<Car> cars = new ArrayList<>();

    @Override
    public void add(Car car) {
    }

    @Override
    public boolean contains(Car car) {
        return false;
    }

    @Override
    public List<Car> findAll() {
        return null;
    }
}
