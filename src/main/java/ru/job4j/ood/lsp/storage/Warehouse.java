package ru.job4j.ood.lsp.storage;

import java.util.ArrayList;
import java.util.List;

public class Warehouse implements Storage {
    private List<Food> foodList = new ArrayList<>();

    @Override
    public boolean check(Food food) {
        boolean rsl = false;
        float percent = staleness(food);
        if (percent < 25) {
            rsl = true;
        }
        return rsl;
    }

    @Override
    public void add(Food food) {
        foodList.add(food);
    }

    @Override
    public List<Food> findAll() {
        return foodList;
    }
}
