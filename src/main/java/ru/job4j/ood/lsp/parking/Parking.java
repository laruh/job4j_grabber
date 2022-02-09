package ru.job4j.ood.lsp.parking;

import java.util.List;

public interface Parking {
    boolean park(Car car);

    boolean canPark(Car car);

    List<Car> getCars();
}
