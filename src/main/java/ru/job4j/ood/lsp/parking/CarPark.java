package ru.job4j.ood.lsp.parking;

import java.util.List;

public class CarPark implements Parking {
    private final Store store;
    private int passengerCapacity;
    private int trackCapacity;

    public CarPark(Store store, int passengerCapacity, int trackCapacity) {
        this.store = store;
        this.passengerCapacity = passengerCapacity;
        this.trackCapacity = trackCapacity;
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    public int getTrackCapacity() {
        return trackCapacity;
    }

    @Override
    public boolean park(Car car) {
        boolean rsl = false;
        int carSize = car.getParkingSize();
        if (carSize == 1 && canPark(car)) {
            store.add(car);
            passengerCapacity -= 1;
            rsl = true;
        } else if (carSize >= 2 && canPark(car)) {
            store.add(car);
            if (trackCapacity >= carSize) {
                trackCapacity -= carSize;
            } else if (passengerCapacity >= carSize) {
                passengerCapacity -= carSize;
            }
            rsl = true;
        }
        return rsl;
    }

    @Override
    public boolean canPark(Car car) {
        boolean rsl = false;
        if (car.getParkingSize() == 1) {
            rsl = !store.contains(car)
                    && passengerCapacity - car.getParkingSize() >= 0;
        } else if (car.getParkingSize() >= 2) {
            rsl = !store.contains(car)
                    && (trackCapacity - car.getParkingSize() >= 0
                    || passengerCapacity - car.getParkingSize() >= 0);
        }
        return rsl;
    }

    @Override
    public List<Car> getCars() {
        return store.findAll();
    }
}
