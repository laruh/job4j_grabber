package ru.job4j.ood.lsp.parking;

import java.util.Objects;

public class Truck implements Car {
    private String id;
    private final int size;

    public Truck(String id, int size) {
        if (size < 2) {
            throw new IllegalArgumentException("Размер грузовой машины должен быть > 1");
        }
        this.id = id;
        this.size = size;
    }

    @Override
    public int getParkingSize() {
        return size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Truck truck = (Truck) o;
        return Objects.equals(id, truck.id);
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
