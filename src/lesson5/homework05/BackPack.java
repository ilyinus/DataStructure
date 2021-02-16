package lesson5.homework05;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BackPack {
    private List<Unit> bestItemsSet;
    private final int maxWeight;
    private int weight;
    private int coast;

    public BackPack(int maxWeight) {
        this.maxWeight = maxWeight;
    }

    private int calculateWeight(List<Unit> items) {
        return items.stream().mapToInt(Unit::getWeight).sum();
    }

    private int calculateCoast(List<Unit> items) {
        return items.stream().mapToInt(Unit::getCoast).sum();
    }

    public void calculateBestSet(List<Unit> items) {

        // Базовое условие
        if (items.size() == 0)
            return;

        int curWeight = calculateWeight(items);
        int curCoast = calculateCoast(items);

        if (curWeight <= maxWeight && curCoast > coast) {
            bestItemsSet = items;
            weight = curWeight;
            coast = curCoast;
        }

        for (Unit item : items) {
            calculateBestSet(new ArrayList<>(items)
                    .stream()
                    .filter(e -> !e.equals(item))
                    .collect(Collectors.toList()));
        }

    }

    @Override
    public String toString() {
        return "BackPack{" +
                "bestItemsSet=" + bestItemsSet +
                ", maxWeight=" + maxWeight +
                ", weight=" + weight +
                ", coast=" + coast +
                '}';
    }

    public static class Unit {
        private final String name;
        private final int weight;
        private final int coast;

        public Unit(String name, int weight, int coast) {
            this.name = name;
            this.weight = weight;
            this.coast = coast;
        }

        public String getName() {
            return name;
        }

        public int getWeight() {
            return weight;
        }

        public int getCoast() {
            return coast;
        }

        @Override
        public String toString() {
            return "Unit{" +
                    "name='" + name + '\'' +
                    ", weight=" + weight +
                    ", coast=" + coast +
                    '}';
        }
    }

    public static void main(String[] args) {
        List<Unit> items = new ArrayList<>();
        items.add(new Unit("iPhone", 4, 3000));
        items.add(new Unit("Alarm clock", 3, 2000));
        items.add(new Unit("Guitar", 1, 1500));

        System.out.println("Искходный набор\n" + items);

        BackPack back = new BackPack(4);
        back.calculateBestSet(items);
        System.out.println("Собранный рюкзак\n" + back);

    }

}
