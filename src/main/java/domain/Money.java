package domain;

import utils.Validator;

public class Money {

    private int money;

    public Money(final String input) {
        validateInputMoney(input);
        this.money = Integer.parseInt(input);
        validateMoney(this.money);
    }

    private void validateInputMoney(final String input) {
        Validator.checkNullOrEmpty(input);
        Validator.checkFormat(input);
    }

    private void validateMoney(final int money) {
        if (money < 1000) {
            throw new IllegalArgumentException("1000원 미만은 입력할 수 없습니다.");
        }
    }

    public int calculateCounts() {
        return this.money / 1_000;
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
        return (double) totalWinPrice / (calculateCounts() * 1000);
    }
}
