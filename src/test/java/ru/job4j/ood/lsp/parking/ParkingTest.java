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
        PassengerCar car = new PassengerCar("656");
        PassengerCar car1 = new PassengerCar("686");
        PassengerParking passenger = new PassengerParking(new MemStore(), 1);
        passenger.park(car);
        List<Car> ex = List.of(new PassengerCar("656"));
        assertFalse(passenger.canPark(car1));
        assertThat(passenger.getCars(), equalTo(ex));
    }

    @Test
    public void whenPassengerCarParkToTrackParking() {
        PassengerCar car = new PassengerCar("656");
        TruckParking truck = new TruckParking(new MemStore(), 3);
        assertFalse(truck.canPark(car));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenPassengerException() {
        PassengerCar car = new PassengerCar("656");
        PassengerCar car2 = new PassengerCar("686");
        PassengerParking parking = new PassengerParking(new MemStore(), 1);
        parking.park(car);
        parking.park(car2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenTruckException() {
        Truck car = new Truck("656", 5);
        TruckParking parking = new TruckParking(new MemStore(), 3);
        parking.park(car);
    }

    @Test
    public void whenTruckToFiveCapacity() {
        Truck car = new Truck("888", 3);
        Truck car1 = new Truck("778", 4);
        Truck car2 = new Truck("123", 2);
        TruckParking parking = new TruckParking(new MemStore(), 5);
        parking.park(car);
        List<Car> ex = List.of(new Truck("888", 3));
        assertFalse(parking.canPark(car1));
        assertTrue(parking.canPark(car2));
        assertThat(parking.getCars(), equalTo(ex));
    }

    @Test
    public void whenTrackParkingContainsCar() {
        Truck car1 = new Truck("888", 3);
        Truck car2 = new Truck("888", 3);
        TruckParking track = new TruckParking(new MemStore(), 8);
        track.park(car1);
        assertFalse(track.canPark(car2));
    }

    @Test
    public void whenPassengerParkingContainsCar() {
        PassengerCar car = new PassengerCar("888");
        PassengerCar car1 = new PassengerCar("888");
        PassengerParking parking = new PassengerParking(new MemStore(), 3);
        parking.park(car);
        assertFalse(parking.canPark(car1));
    }
}