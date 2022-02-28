package lotto.model;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

public enum Rank {

    FIRST(6, 2_000_000_000),
    SECOND(5, 30_000_000),
    THIRD(5, 1_500_000),
    FOURTH(4, 50_000),
    FIFTH(3, 5_000),
    LOSER(0, 0);

    private static final int DEFAULT_COUNT = 0;

    private final int count;
    private final int price;

    Rank(int count, int price) {
        this.count = count;
        this.price = price;
    }

    public static Rank of(int count, boolean bonusNumber) {
        return Arrays.stream(Rank.values())
            .filter(rank -> rank.count == count)
            .filter(rank -> !rank.equals(SECOND) || bonusNumber)
            .findFirst()
            .orElse(LOSER);
    }

    public static Map<Rank, Integer> initMap() {
        Map<Rank, Integer> map = new EnumMap<>(Rank.class);
        Arrays.stream(Rank.values())
            .forEach(rank -> map.put(rank, DEFAULT_COUNT));
        return map;
    }

    public int getCount() {
        return count;
    }

    public int getPrice() {
        return price;
    }

    public boolean isSecond() {
        return this.price == SECOND.getPrice();
    }

    public int sum(int count) {
        return this.price * count;
    }
}
