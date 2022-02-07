package ru.job4j.ood.lsp.parking;

import java.util.Objects;

public class PassengerCar implements Car {
    private final String id;
    private final int size = 1;

    public PassengerCar(String id) {
        this.id = id;
    }

    @Override
    public int getParkingSize() {
        return this.size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PassengerCar that = (PassengerCar) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "PassengerCar{"
                + "id='" + id
                + '\''
                + ", size=" + size
                + '}';
    }
}
