package model;

import java.util.Objects;

public class LottoPurchasingMoney {
    static final String NOT_ENOUGH_MONEY = "[ERROR] 로또를 구매하려면 최소 천원 이상 투입해야 합니다.";

    private static final int LOTTO_PRICE = 1000;

    private final int amount;

    private LottoPurchasingMoney(int amount) {
        validateEnough(amount);
        this.amount = amount;
    }

    public static LottoPurchasingMoney valueOf(int amount) {
        return new LottoPurchasingMoney(amount);
    }

    public int getAmount() {
        return amount;
    }

    private void validateEnough(int amount) {
        if (amount < LOTTO_PRICE) {
            throw new IllegalArgumentException(NOT_ENOUGH_MONEY);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LottoPurchasingMoney lottoPurchasingMoney = (LottoPurchasingMoney) o;
        return amount == lottoPurchasingMoney.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
}
