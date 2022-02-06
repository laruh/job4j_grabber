package ru.job4j.ood.lsp.storage;

import java.util.List;

public class ControllQuality {
    private List<Storage> storages;

    public ControllQuality(List<Storage> storages) {
        this.storages = storages;
    }

    public void sorting(Food food) {
        for (Storage storage : storages) {
            if (storage.check(food)) {
                storage.add(food);
                break;
            }
        }
    }
}
