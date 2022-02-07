package ru.job4j.ood.lsp.parking;

import java.util.List;

public class TruckParking implements Parking {
    private final Store store;
    private final int capacity;
    private int full = 0;

    public TruckParking(Store store, int capacity) {
        this.store = store;
        this.capacity = capacity;
    }

    @Override
    public void park(Car car) {
    }

    @Override
    public boolean canPark(Car car) {
        return false;
    }

    @Override
    public List<Car> getCars() {
        return null;
    }
}
