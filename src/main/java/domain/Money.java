package domain;

import utils.Validator;

public class Money {

    private static final int LOTTO_PRICE = 1_000;
    private static final String ERROR_LOWER_THAN_LOTTO_PRICE_MESSAGE = "원 미만은 입력할 수 없습니다.";

    private int money;

    public Money(final String input) {
        validateInputMoney(input);
        this.money = Integer.parseInt(input);
        validateMoneyRange(this.money);
    }

    private void validateInputMoney(final String input) {
        Validator.checkNullOrEmpty(input);
        Validator.checkFormat(input);
    }

    private void validateMoneyRange(final int money) {
        if (money < LOTTO_PRICE) {
            throw new IllegalArgumentException(LOTTO_PRICE + ERROR_LOWER_THAN_LOTTO_PRICE_MESSAGE);
        }
    }

    public int calculateCounts() {
        return this.money / LOTTO_PRICE;
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        final Money money1 = (Money) object;

        return money == money1.money;
    }

    @Override
    public int hashCode() {
        return money;
    }

    public double calculateProfit(final int totalWinPrice) {
        return (double) totalWinPrice / (calculateCounts() * LOTTO_PRICE);
    }
}
