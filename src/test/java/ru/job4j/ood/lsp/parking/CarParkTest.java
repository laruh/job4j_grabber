package ru.job4j.ood.lsp.parking;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CarParkTest {

    @Test
    public void whenTruckParkToPassengerPlace() {
        PassengerCar car = new PassengerCar("991");
        PassengerCar car1 = new PassengerCar("686");
        Truck car2 = new Truck("123", 3);
        Truck car3 = new Truck("455", 2);
        CarPark carPark = new CarPark(4, 1);
        carPark.park(car);
        carPark.park(car1);
        carPark.park(car2);
        assertTrue(carPark.canPark(car3));
        carPark.park(car3);
        List<Car> ex = List.of(new PassengerCar("991"), new PassengerCar("686"),
                new Truck("123", 3), new Truck("455", 2));
        assertThat(carPark.getCars(), equalTo(ex));
        assertThat(carPark.getPassengerCapacity(), equalTo(0));
        assertThat(carPark.getTrackCapacity(), equalTo(0));
    }

    @Test
    public void whenAllCarsParkToTheirTypePlace() {
        PassengerCar car = new PassengerCar("991");
        PassengerCar car1 = new PassengerCar("686");
        Truck car2 = new Truck("444", 4);
        Truck car3 = new Truck("123", 3);
        CarPark carPark = new CarPark(3, 2);
        carPark.park(car);
        carPark.park(car1);
        carPark.park(car2);
        List<Car> ex = List.of(new PassengerCar("991"), new PassengerCar("686"),
                new Truck("444", 4));
        assertThat(carPark.getCars(), equalTo(ex));
        assertTrue(carPark.canPark(car3));
        assertThat(carPark.getPassengerCapacity(), equalTo(1));
        assertThat(carPark.getTrackCapacity(), equalTo(1));
    }

    @Test
    public void whenSamePassengerCarAlreadyParked() {
        PassengerCar car = new PassengerCar("656");
        PassengerCar car1 = new PassengerCar("656");
        PassengerCar car2 = new PassengerCar("888");
        CarPark carPark = new CarPark(2, 1);
        carPark.park(car);
        assertFalse(carPark.canPark(car1));
        assertTrue(carPark.park(car2));
    }

    @Test
    public void whenSameTruckAlreadyParked() {
        Truck car = new Truck("656", 2);
        Truck car1 = new Truck("656", 2);
        Truck car2 = new Truck("888", 3);
        CarPark carPark = new CarPark(1, 2);
        carPark.park(car);
        assertFalse(carPark.canPark(car1));
        assertTrue(carPark.park(car2));
    }
}