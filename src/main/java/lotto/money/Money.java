package lotto.money;

import lotto.ticket.Ticket;

import java.util.Objects;

import static lotto.ticket.Number.validateNumber;

public class Money {
    private static final int SECOND_DECIMAL_POINT_MAKER = 100;
    public static final String ERROR_MESSAGE_MINIMUM_MONEY = Ticket.PRICE + "원 이상의 금액이 필요합니다.";

    private final int money;

    public Money(String money) {
        this.money = validate(money);
    }

    private int validate(String value) {
        int money = validateNumber(value);
        validateMinimumPrice(money);
        return money;
    }

    private void validateMinimumPrice(int value) {
        if (value < Ticket.PRICE) {
            throw new IllegalArgumentException(ERROR_MESSAGE_MINIMUM_MONEY);
        }
    }

    public String divideMoney(int unit) {
        return String.valueOf(this.money / unit);
    }

    public String calculateProfit(Money totalMoney) {
        double profit = Math.floor((double) totalMoney.money / money * SECOND_DECIMAL_POINT_MAKER) / SECOND_DECIMAL_POINT_MAKER;
        return Double.toString(profit);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Money that = (Money) o;
        return money == that.money;
    }

    @Override
    public int hashCode() {
        return Objects.hash(money);
    }
}
