package lotto.domain;

import static lotto.constant.ErrorMessage.USED_MONEY_MUST_NOT_ZERO;

import java.util.Map;

public class Bank {
    private int usedMoney = 0;

    public void use(int money) {
        usedMoney += money;
    }

    public double calculateRateOfReturn(Map<Rank, Integer> result) {
        validateUsedMoney();
        long sum = result.entrySet().stream()
            .mapToLong(set -> set.getKey().getPrice() * set.getValue())
            .sum();
        return (double) sum / usedMoney;
    }

    private void validateUsedMoney() {
        if (usedMoney == 0) {
            throw new IllegalArgumentException(USED_MONEY_MUST_NOT_ZERO.getMessage());
        }
    }
}
