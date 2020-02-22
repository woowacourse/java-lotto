package lotto.controller;

import lotto.domain.Money;
import lotto.domain.Rank;

import java.util.List;
import java.util.Objects;

public class AccountingManager {
    public static double calculateEarningRate(Money money, List<Rank> ranks) {
        int sum = ranks.stream()
                .filter(Objects::nonNull)
                .mapToInt(Rank::getReward)
                .sum();
        return (double) sum / money.getMoney() * 100;
    }
}
