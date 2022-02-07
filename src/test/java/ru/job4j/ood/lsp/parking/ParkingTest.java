package ru.job4j.ood.lsp.parking;

import org.junit.Test;

import java.util.List;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ParkingTest {

    @Test
    public void whenPassengerCarPark() {
        Car car = new PassengerCar();
        Car car1 = new PassengerCar();
        Parking passenger = new PassengerParking(new MemStore(), 1);
        passenger.park(car);
        assertFalse(passenger.canPark(car1));
        assertThat(passenger.getCars(), equalTo(null));
    }

    @Test
    public void whenPassengerCarParkToTrackParking() {
        Car car = new PassengerCar();
        Parking truck = new TruckParking(new MemStore(), 3);
        assertFalse(truck.canPark(car));
    }

    @Test
    public void whenTruckToFiveCapacity() {
        Car car = new Truck(3);
        Car car1 = new Truck(4);
        Car car2 = new Truck(2);
        Parking parking = new TruckParking(new MemStore(), 5);
        parking.park(car);
        List<Car> ex = List.of(new Truck(3));
        assertFalse(parking.canPark(car1));
        assertFalse(parking.canPark(car2));
        assertThat(parking.getCars(), equalTo(null));
    }

    @Test
    public void whenTrackParkingContainsCar() {
        Car car1 = new Truck(3);
        Car car2 = new Truck(3);
        Parking track = new TruckParking(new MemStore(), 8);
        track.park(car1);
        assertFalse(track.canPark(car2));
    }

    @Test
    public void whenPassengerParkingContainsCar() {
        Car car = new PassengerCar();
        Car car1 = new PassengerCar();
        Parking parking = new PassengerParking(new MemStore(), 3);
        parking.park(car);
        assertFalse(parking.canPark(car1));
    }
}