package lotto.domain;

import java.util.Objects;

public class Money {

    private static final int BASIC_LOTTO_MONEY = 1000;
    private static final String ERROR_WRONG_INPUT_MONEY = "[ERROR] 구입금액으로 " + BASIC_LOTTO_MONEY + " 이상의 숫자를 입력해주세요.";
    private static final String ERROR_WRONG_DIVIDING_MONEY = "[ERROR] 구입금액으로 " + BASIC_LOTTO_MONEY + "으로 나누어 떨어지는 수를 입력해주세요.";

    private final int money;

    public Money(final String money) {
        checkValidMoney(money);
        this.money = Integer.parseInt(money);
    }

    private void checkValidMoney(final String money) {
        checkValidInt(money);
        checkMoreThanBasicLottoMoney(Integer.parseInt(money));
        checkDividingMoney(Integer.parseInt(money));
    }

    private void checkValidInt(final String number) {
        try {
            Integer.parseInt(number);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(ERROR_WRONG_INPUT_MONEY);
        }
    }

    private void checkMoreThanBasicLottoMoney(final int money) {
        if (money < BASIC_LOTTO_MONEY) {
            throw new IllegalArgumentException(ERROR_WRONG_INPUT_MONEY);
        }
    }

    private void checkDividingMoney(final int money) {
        if (money % BASIC_LOTTO_MONEY != 0) {
            throw new IllegalArgumentException(ERROR_WRONG_DIVIDING_MONEY);
        }
    }

    public int purchasedLottoCount() {
        return money / BASIC_LOTTO_MONEY;
    }

    public int getMoney() {
        return money;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Money)) {
            return false;
        }
        Money money1 = (Money) o;
        return getMoney() == money1.getMoney();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMoney());
    }
}
