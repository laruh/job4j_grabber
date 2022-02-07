package ru.job4j.ood.lsp.storage;

import java.util.ArrayList;
import java.util.List;

public class Trash implements Storage {
    private List<Food> foodList = new ArrayList<>();

    @Override
    public boolean check(Food food) {
        boolean rsl = false;
        float percent = staleness(food);
        if (percent >= 100) {
            rsl = true;
        }
        return rsl;
    }

    @Override
    public boolean add(Food food) {
        boolean rsl = false;
        if (check(food)) {
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
