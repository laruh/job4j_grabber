package ru.job4j.ood.lsp.storage;

import org.junit.Test;

import java.time.LocalDate;
import java.util.List;
import static org.junit.Assert.*;

public class ControllQualityTest {

    @Test
    public void whenSorting() {
        LocalDate date = LocalDate.now();
        Food chocolate = new Food("Chocolate", date.plusDays(30), date.minusDays(30), 89);
        Food soda = new Food("Soda", date.plusMonths(8), date.minusMonths(1), 49);
        Food milk = new Food("Milk", date.plusDays(1), date.minusDays(5), 69);
        Food cheese = new Food("Cheese", date.minusDays(2), date.minusDays(6), 109);
        Shop shop = new Shop();
        Warehouse warehouse = new Warehouse();
        Trash trash = new Trash();
        List<Storage> storages = List.of(shop, warehouse, trash);
        ControllQuality quality = new ControllQuality(storages);
        quality.sorting(chocolate);
        quality.sorting(milk);
        quality.sorting(soda);
        quality.sorting(cheese);
        assertTrue(warehouse.findAll().contains(soda));
        assertTrue(trash.findAll().contains(cheese));
        assertEquals(2, shop.findAll().size());
        assertEquals(15, milk.getDiscount(), 0.5);
    }
}
