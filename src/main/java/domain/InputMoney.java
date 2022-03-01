package domain;

import java.util.Objects;

public class InputMoney {
    static final String ERROR_MESSAGE_FOR_MINIMUM_NUMBER = "1000 보다 작은 금액을 입력할 수 없습니다.";
    static final String ERROR_MESSAGE_FOR_MULTIPLE_OF_UNIT = "1000으로 나누어 떨어지지 않는 금액을 입력할 수 없습니다.";

    private static final int MINIMUM_INPUT_MONEY = 1000;
    private static final int REMAIN = 0;

    private final int money;

    public InputMoney(int money) {
        validateMinimum(money);
        validateMultiple(money);
        this.money = money;
    }

    private void validateMinimum(int money) {
        if (money < MINIMUM_INPUT_MONEY) {
            throw new IllegalArgumentException(ERROR_MESSAGE_FOR_MINIMUM_NUMBER);
        }
    }

    private void validateMultiple(int money) {
        if (money % Lotto.SINGLE_LOTTO_PRICE != REMAIN) {
            throw new IllegalArgumentException(ERROR_MESSAGE_FOR_MULTIPLE_OF_UNIT);
        }
    }

    public int getMoney() {
        return money;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        InputMoney that = (InputMoney) object;
        return money == that.money;
    }

    @Override
    public int hashCode() {
        return Objects.hash(money);
    }

    @Override
    public String toString() {
        return "InputMoney{" +
                "money=" + money +
                '}';
    }
}
