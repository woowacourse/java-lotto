package domain;

import domain.excepetion.AmountExceptionMessage;

public class Amount {

    public static final int LOTTO_PRICE = 1000;

    private final int amount;

    public Amount(String inputPrice) {
        int price = validateIsInteger(inputPrice);
        validateIsPositive(price);
        validatePerPrice(price);
        this.amount = price / LOTTO_PRICE;
    }

    public boolean compareAmount(int amount) {
        return this.amount > amount;
    }

    public double calculateProfit(long sum) {
        return Math.floor((double) sum / (amount * LOTTO_PRICE) * 100) / 100;
    }

    private void validateIsPositive(int price) {
        if (price <= 0) {
            throw new IllegalArgumentException(AmountExceptionMessage.INVALID_POSITIVE);
        }
    }

    private int validateIsInteger(String inputPrice) {
        try {
            return Integer.parseInt(inputPrice);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(AmountExceptionMessage.INVALID_INTEGER);
        }
    }

    private void validatePerPrice(int price) {
        if (price % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException(AmountExceptionMessage.INVALID_UNIT_PRICE);
        }
    }

    public int getAmount() {
        return amount;
    }

}
