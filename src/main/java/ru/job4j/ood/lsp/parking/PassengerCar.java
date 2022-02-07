package ru.job4j.ood.lsp.parking;

public class PassengerCar implements Car {
    private final int size = 1;

    public PassengerCar() {
    }

    @Override
    public int getParkingSize() {
        return this.size;
    }
}
