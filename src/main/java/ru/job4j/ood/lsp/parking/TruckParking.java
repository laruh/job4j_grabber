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
        if (!canPark(car)) {
            throw  new IllegalArgumentException("You can not park this car");
        }
        store.add(car);
        full += car.getParkingSize();
    }

    @Override
    public boolean canPark(Car car) {
        return !store.contains(car)
                && capacity - full - car.getParkingSize() >= 0
                && car.getParkingSize() >= 2;
    }

    @Override
    public List<Car> getCars() {
        return store.findAll();
    }
}
