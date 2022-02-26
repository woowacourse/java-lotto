package vo;

import static controller.LottoController.LOTTO_PRICE;

public class InputMoney {
    private static final int REMAIN = 0;
    private static final String ERROR_MESSAGE_FOR_INVALID_INPUT_MONEY = "금액은 1000원 이상, 1000원 단위로 입력해주세요";

    private final int money;

    public InputMoney(int money) {
        validate(money);
        this.money = money;
    }

    private void validate(int money) {
        if (isInvalidInputMoney(money)) {
            throw new IllegalArgumentException(ERROR_MESSAGE_FOR_INVALID_INPUT_MONEY);
        }
    }

    private boolean isInvalidInputMoney(int money) {
        return money < LOTTO_PRICE || money % LOTTO_PRICE != REMAIN;
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
