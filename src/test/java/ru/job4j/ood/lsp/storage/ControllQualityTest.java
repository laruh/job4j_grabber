package ru.job4j.ood.lsp.storage;

import org.junit.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;
import static org.junit.Assert.*;

public class ControllQualityTest {

    @Test
    public void foodShop() {
        Food milk = new Food("Milk new",
                LocalDate.of(2022, 2, 10),
                LocalDate.of(2022, 2, 4),
                56.5);
        Food chocolate = new Food("Chocolate",
                LocalDate.of(2022, 2, 8),
                LocalDate.of(2021, 1, 5),
                10);
        List<Food> foodList = List.of(milk, chocolate);

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

        Food milkShop = new Food("Milk new",
                LocalDate.of(2022, 2, 10),
                LocalDate.of(2022, 2, 4),
                56.5);
        milkShop.setDiscount(0);
        Food chocoShop = new Food("Chocolate",
                LocalDate.of(2022, 2, 8),
                LocalDate.of(2021, 1, 5),
                10);
        chocoShop.setDiscount(15);
        List<Food> shopExp = List.of(milkShop, chocoShop);
        assertEquals(shopExp, cQuality.getShop().findAll());
    }

    @Test
    public void foodWarehouse() {
        Food soda = new Food("Soda new",
                LocalDate.of(2022, 12, 2),
                LocalDate.of(2021, 11, 2),
                10);
        List<Food> foodList = List.of(soda);

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

        Food sodaWare = new Food("Soda new",
                LocalDate.of(2022, 12, 2),
                LocalDate.of(2021, 11, 2),
                10);
        sodaWare.setDiscount(0);

        List<Food> warehouseExp = List.of(sodaWare);
        assertEquals(warehouseExp, cQuality.getWarehouse().findAll());
    }

    @Test
    public void foodSorted() {
        Food badCheese = new Food("Bad Cheese",
                LocalDate.of(2022, 2, 2),
                LocalDate.of(2021, 1, 16),
                10);
        List<Food> foodList = List.of(badCheese);

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

        Food cheeseTrash = new Food("Bad Cheese",
                LocalDate.of(2022, 2, 2),
                LocalDate.of(2021, 1, 16),
                10);
        cheeseTrash.setDiscount(0);
        List<Food> trashExp = List.of(cheeseTrash);
        assertEquals(trashExp, cQuality.getTrash().findAll());
    }
}
