package domain;

import global.exception.ExceptionMessage;
import java.util.EnumMap;
import java.util.Map.Entry;

public class Amount {
    public static final int LOTTO_PRICE = 1000;

    private final int amount;

    public Amount(int price) {
        validateIsPositive(price);
        validatePerPrice(price);
        this.amount = price / LOTTO_PRICE;
    }

    public boolean compareAmount(int amount) {
        return this.amount > amount;
    }

    public int getAmount() {
        return amount;
    }

    private void validateIsPositive(int price) {
        if (price <= 0) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_POSITIVE.getMessage());
        }
    }

    private void validatePerPrice(int price) {
        if (price % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_UNIT_PRICE.getMessage());
        }
    }

    public double calculateProfit(EnumMap<Rank, Integer> countRank) {
        long sum = 0L;
        for (Entry<Rank, Integer> rankIntegerEntry : countRank.entrySet()) {
            sum += Rank.calculateTotalPrize(rankIntegerEntry.getKey(), rankIntegerEntry.getValue());
        }

        return Math.floor((double) sum / (amount * LOTTO_PRICE) * 100) / 100;
    }
}
