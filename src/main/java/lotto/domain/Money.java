package lotto.domain;

public class Money {

    private final int value;

    public Money(String moneyInput) {
        if (!isNumeric(moneyInput)) {
            throw new IllegalArgumentException();
        }
        int money = Integer.parseInt(moneyInput);

        if (money < 0) {
            throw new IllegalArgumentException();
        }

        value = money;
    }

    public int getValue() {
        return value;
    }

    private Boolean isNumeric(String value) {
        try {
            Integer.parseInt(value);
        } catch (NumberFormatException numberFormatException) {
            return false;
        }
        return true;
    }
}
