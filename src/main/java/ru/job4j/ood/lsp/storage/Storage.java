package ru.job4j.ood.lsp.storage;

import java.time.LocalDate;
import java.util.List;
import java.time.temporal.ChronoUnit;

public interface Storage {

    boolean check(Food food);

    default float staleness(Food food) {
        long a = ChronoUnit.DAYS.between(food.getCreateDate(), LocalDate.now());
        long b = ChronoUnit.DAYS.between(food.getCreateDate(), food.getExpiryDate());
        return  ((float) a / b) * 100;
    }

    boolean add(Food food);

    List<Food> findAll();
}
