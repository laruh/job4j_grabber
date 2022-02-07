package ru.job4j.ood.lsp.storage;

import java.util.ArrayList;
import java.util.List;

public class Shop implements Storage {
    private List<Food> foodList = new ArrayList<>();

    @Override
    public boolean check(Food food) {
        boolean rsl = false;
        float percent = staleness(food);
        if (percent >= 25 && percent < 100) {
            rsl = true;
        }
        return rsl;
    }

    @Override
    public boolean add(Food food) {
        boolean rsl = false;
        if (check(food)) {
            float percent = staleness(food);
            if (percent >= 75 && percent < 100) {
                food.setDiscount(15);
            }
            foodList.add(food);
            rsl = true;
        }
        return rsl;
    }

    @Override
    public List<Food> findAll() {
        return List.copyOf(foodList);
    }
}
