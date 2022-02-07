package ru.job4j.ood.lsp.parking;

import java.util.List;

public interface Store {
    void add(Car car);

    boolean contains(Car car);

    List<Car> findAll();
}
