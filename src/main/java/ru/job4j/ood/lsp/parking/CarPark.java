package ru.job4j.ood.lsp.parking;

import java.util.List;

public class CarPark implements Parking {
    private final MemStore store = new MemStore();
    private int passengerCapacity;
    private int trackCapacity;

    public CarPark(int passengerCapacity, int trackCapacity) {
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
        if (carSize == PassengerCar.SIZE && canPark(car)) {
            store.add(car);
            passengerCapacity -= 1;
            rsl = true;
        } else if (carSize >= 2 && canPark(car)) {
            store.add(car);
            if (trackCapacity - 1 >= 0) {
                trackCapacity -= 1;
            } else if (passengerCapacity - carSize >= 0) {
                passengerCapacity -= carSize;
            }
            rsl = true;
        }
        return rsl;
    }

    @Override
    public boolean canPark(Car car) {
        boolean rsl = false;
        if (car.getParkingSize() == PassengerCar.SIZE) {
            rsl = !store.contains(car)
                    && passengerCapacity - car.getParkingSize() >= 0;
        } else if (car.getParkingSize() >= 2) {
            rsl = !store.contains(car)
                    && (trackCapacity - 1 >= 0
                    || passengerCapacity - car.getParkingSize() >= 0);
        }
        return rsl;
    }

    @Override
    public List<Car> getCars() {
        return store.findAll();
    }
}
