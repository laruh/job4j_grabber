package ru.job4j.ood.lsp.storage;

import org.junit.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;
import static org.junit.Assert.*;

public class ControllQualityTest {

    @Test
    public void foodSorted() {
        Food milk = new Food("Milk",
                LocalDate.of(2022, 2, 10),
                LocalDate.of(2022, 2, 4),
                56.5);
        Food soda = new Food("Soda",
                LocalDate.of(2022, 12, 2),
                LocalDate.of(2021, 12, 2),
                10);
        Food badCheese = new Food("Bad Cheese",
                LocalDate.of(2022, 2, 2),
                LocalDate.of(2021, 1, 16),
                10);
        Food chocolate = new Food("Chocolate",
                LocalDate.of(2022, 2, 8),
                LocalDate.of(2021, 1, 5),
                10);
        List<Food> foodList = List.of(milk, soda, badCheese, chocolate);

        Warehouse warehouse = new Warehouse();
        Shop shop = new Shop();
        Trash trash = new Trash();

        Predicate<Food> warehousePred = food -> warehouse.staleness(food) < 25;
        Predicate<Food> trashPred = food -> trash.staleness(food) >= 100;
        Predicate<Food> shopPred1 = food -> shop.staleness(food) >= 25
                && shop.staleness(food) < 75;
        Predicate<Food> shopPred2 = food -> shop.staleness(food) >= 75
                && shop.staleness(food) < 100;

        ControllQuality cQuality = new ControllQuality(foodList, warehouse, shop, trash);
        cQuality.foodStorage(warehousePred, trashPred, shopPred1, shopPred2);

        Food sodaWare = new Food("Soda",
                LocalDate.of(2022, 12, 2),
                LocalDate.of(2021, 12, 2),
                10);
        sodaWare.setDiscount(0);
        Food milkShop = new Food("Milk",
                LocalDate.of(2022, 2, 10),
                LocalDate.of(2022, 2, 4),
                56.5);
        milkShop.setDiscount(0);
        Food chocoShop = new Food("Chocolate",
                LocalDate.of(2022, 2, 8),
                LocalDate.of(2021, 1, 5),
                10);
        chocoShop.setDiscount(15);
        Food cheeseTrash = new Food("Bad Cheese",
                LocalDate.of(2022, 2, 2),
                LocalDate.of(2021, 1, 16),
                10);
        cheeseTrash.setDiscount(0);
        List<Food> warehouseExp = List.of(sodaWare);
        List<Food> shopExp = List.of(milkShop, chocoShop);
        List<Food> trashExp = List.of(cheeseTrash);
        assertEquals(warehouseExp, cQuality.getWarehouse().findAll());
        assertEquals(shopExp, cQuality.getShop().findAll());
        assertEquals(trashExp, cQuality.getTrash().findAll());
    }
}
