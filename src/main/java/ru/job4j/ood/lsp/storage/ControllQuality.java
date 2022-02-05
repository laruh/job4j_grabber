package ru.job4j.ood.lsp.storage;

import java.util.List;
import java.util.function.Predicate;

public class ControllQuality {
    private List<Food> foodList;

    private Storage warehouse;

    private Storage shop;

    private Storage trash;

    public ControllQuality(List<Food> foodList, Storage warehouse, Storage shop, Storage trash) {
        this.foodList = foodList;
        this.warehouse = warehouse;
        this.shop = shop;
        this.trash = trash;
    }

    public Storage getWarehouse() {
        return warehouse;
    }

    public Storage getShop() {
        return shop;
    }

    public Storage getTrash() {
        return trash;
    }

    public void foodStorage(Predicate<Food> warehousePred, Predicate<Food> trashPred,
                            Predicate<Food> shopPred1, Predicate<Food> shopPred2) {
        for (Food el : foodList) {
            if (warehouse.check(warehousePred, el)) {
                warehouse.add(el);
            } else if (trash.check(trashPred, el)) {
                trash.add(el);
            } else if (shop.check(shopPred1, el)) {
                shop.add(el);
            } else if (shop.check(shopPred2, el)) {
                el.setDiscount(15);
                shop.add(el);
            }
        }
    }
}
