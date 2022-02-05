package ru.job4j.ood.lsp.storage;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;
import java.time.temporal.ChronoUnit;

public interface Storage {

    default boolean check(Predicate<Food> predicate, Food food) {
        return predicate.test(food);
    }

    default float staleness(Food food) {
        long a = ChronoUnit.DAYS.between(food.getCreateDate(), LocalDate.now());
        long b = ChronoUnit.DAYS.between(food.getCreateDate(), food.getExpiryDate());
        return  ((float) a / b) * 100;
    }

    void add(Food food);

    List<Food> findAll();
}
