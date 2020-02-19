package lotto.domain;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class Statistics {
    private static final int ADD = 1;
    private final Map<PrizeGroup, Integer> statistics;

    public Statistics(List<PrizeGroup> prizeGroup) {
        this.statistics = new EnumMap<>(PrizeGroup.class);
        for (PrizeGroup prize : prizeGroup) {
            collectPrize(prize);
        }
    }

    private void collectPrize(PrizeGroup prize) {
        if (this.statistics.containsKey(prize)) {
            Integer count = this.statistics.get(prize);
            this.statistics.put(prize, count + ADD);
            return;
        }
        this.statistics.put(prize, ADD);
    }

    public int getPrizeCount(PrizeGroup prizeGroup) {
        return statistics.getOrDefault(prizeGroup, 0);
    }

    public double getRate() {
        double bettingMoney = getBettingMoney();
        double totalPrize = getPrize();
        return totalPrize / bettingMoney;
    }

    private int getBettingMoney() {
        return statistics.values().stream()
                .mapToInt(Integer::valueOf)
                .sum() * 1000;
    }

    private double getPrize() {
        double total = 0;
        for (Map.Entry<PrizeGroup, Integer> entry : statistics.entrySet()) {
            int money = entry.getKey()
                    .getMoney();
            int size = entry.getValue();
            total += money * size;
        }
        return total;
    }
}
