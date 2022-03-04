package model;

import java.util.Objects;

public class LottoPurchasingMoney {
    private static final String NOT_ENOUGH_MONEY = "[ERROR] 로또를 구매하려면 최소 천원 이상 투입해야 합니다.";

    private static final int LOTTO_PRICE = 1000;

    private final int value;

    private LottoPurchasingMoney(int value) {
        validateEnough(value);
        this.value = value / LOTTO_PRICE * LOTTO_PRICE;
    }

    public static LottoPurchasingMoney valueOf(int money) {
        return new LottoPurchasingMoney(money);
    }

    public int getValue() {
        return value;
    }

    public int getPurchasableCount() {
        return value / LOTTO_PRICE;
    }

    private void validateEnough(int value) {
        if (value < LOTTO_PRICE) {
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
        return value == lottoPurchasingMoney.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
