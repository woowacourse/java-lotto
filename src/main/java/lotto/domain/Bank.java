package lotto.domain;

import java.util.Map;

public class Bank {
    private int usedMoney = 0;

    public void use(int money) {
        usedMoney += money;
    }

    public double calculateRateOfReturn(Map<Rank, Integer> result) {
        long sum = result.entrySet().stream()
            .mapToLong(set -> set.getKey().getPrice() * set.getValue())
            .sum();
        return (double) sum / usedMoney;
    }
}
