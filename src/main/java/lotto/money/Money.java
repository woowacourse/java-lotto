package lotto.money;

import lotto.lottoticket.TicketValidation;

import java.util.Objects;

import static lotto.lottogame.LottoCount.LOTTO_PRICE;

public class Money {
    public static final String ERROR_MESSAGE_MINIMUM_MONEY = "1000원 이상의 금액이 필요합니다.";
    private static final int SECOND_DECIMAL_POINT_MAKER = 100;

    private final int money;

    public Money(String money) {
        this.money = validate(money);
    }

    private int validate(String money) {
        int value = TicketValidation.validateNumber(money);
        checkMinimum(value);
        return value;
    }

    private void checkMinimum(int value) {
        if (value < LOTTO_PRICE) {
            throw new IllegalArgumentException(ERROR_MESSAGE_MINIMUM_MONEY);
        }
    }

    public int divideMoney(int unit) {
        return this.money / unit;
    }

    public Double divideByMoney(int totalMoney) {
        return Math.floor((double) totalMoney / money * SECOND_DECIMAL_POINT_MAKER) / SECOND_DECIMAL_POINT_MAKER;
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
