package ru.job4j.ood.lsp;

import java.time.LocalDate;
import java.time.Month;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

class WorkDays implements Iterable<Integer> {

    private Map<LocalDate, Integer> workDays = new LinkedHashMap<>();

    public void add(LocalDate date, int hours) {
        workDays.put(date, hours);
    }

    @Override
    public Iterator<Integer> iterator() {
        return workDays.values().iterator();
    }
}

class CountingRoom {

    private int normHours;

    private int payPerHour;

    public CountingRoom(int normHours, int payPerHour) {
        this.normHours = normHours;
        this.payPerHour = payPerHour;
    }

    public int getPayPerHour() {
        return payPerHour;
    }

    public int pay(WorkDays workDays) {
        int factHours = 0;
        for (Integer hoursPerDay : workDays) {
            factHours += hoursPerDay;
        }
        if (factHours < normHours) {
            throw new IllegalArgumentException("Worker didn't work enough!");
        }
        return factHours * payPerHour;
    }

}

class ShopCountingRoom extends CountingRoom {

    public ShopCountingRoom(int normHours, int payPerHour) {
        super(normHours, payPerHour);
    }

    @Override
    public int pay(WorkDays workDays) {
        int factHours = 0;
        for (Integer hoursPerDay : workDays) {
            factHours += hoursPerDay;
        }
        return factHours * getPayPerHour();
    }
}

public class SecondRule {
    public static void main(String[] args) {

        WorkDays workDays = new WorkDays();
        workDays.add(LocalDate.of(2020, Month.DECEMBER, 1), 8);
        workDays.add(LocalDate.of(2020, Month.DECEMBER, 2), 6);
        workDays.add(LocalDate.of(2020, Month.DECEMBER, 3), 7);

        CountingRoom countingRoom = new ShopCountingRoom(3 * 8, 500);
        System.out.println(countingRoom.pay(workDays));
    }
}

class Bank {
    private String nameTransaction;

    public Bank(String nameTransaction) {
        this.nameTransaction = nameTransaction;
    }

    public String getNameTransaction() {
        return nameTransaction;
    }

    public void transfer(String nameTransaction) {
        if (!nameTransaction.contains("ruble")) {
            throw new IllegalArgumentException("Use local currency");
        }
        if (nameTransaction.contains("dollar")) {
            throw new IllegalArgumentException("You can not transfer dollars");
        }
        System.out.println("Completed successfully ");
    }
}

class TinkoffBank extends Bank {

    public TinkoffBank(String nameTransaction) {
        super(nameTransaction);
    }

    @Override
    public void transfer(String nameTransaction) {
        if (!nameTransaction.contains("ruble")) {
            throw new IllegalArgumentException("Use local currency");
        }
        System.out.println("Completed successfully ");
    }
}
