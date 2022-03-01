package vo;

import static domain.Lotto.LOTTO_PRICE;

public class Money {
    private static final int EXPECTED_REMAIN = 0;
    private static final String ERROR_MESSAGE_FOR_INVALID_INPUT_MONEY = "금액은 1000원 이상, 1000원 단위로 입력해주세요";

    private int money;

    public Money(int money) {
        validate(money);
        this.money = money;
    }

    private void validate(int money) {
        if (isInvalidInputMoney(money)) {
            throw new IllegalArgumentException(ERROR_MESSAGE_FOR_INVALID_INPUT_MONEY);
        }
    }

    private boolean isInvalidInputMoney(int money) {
        return money < LOTTO_PRICE || money % LOTTO_PRICE != EXPECTED_REMAIN;
    }

    public int getMoney() {
        return money;
    }

    @Override
    public String toString() {
        return "InputMoney{" +
                "money=" + money +
                '}';
    }
}
