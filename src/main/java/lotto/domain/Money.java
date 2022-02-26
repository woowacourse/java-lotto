package lotto.domain;

public class Money {

    public static final int BASIC_LOTTO_MONEY = 1000;
    public static final String ERROR_WRONG_INPUT_MONEY = "[ERROR] 구입금액으로 " + BASIC_LOTTO_MONEY + "이상의 숫자를 입력해주세요.";
    public static final String ERROR_WRONG_DIVIDING_MONEY = "[ERROR] 구입금액으로 " + BASIC_LOTTO_MONEY + "으로 나누어 떨어지는 수를 입력해주세요.";

    private final int money;

    public Money(final String money) {
        checkValidate(money);
        this.money = Integer.parseInt(money);
    }

    public int purchasedLottoAmount() {
        return money / BASIC_LOTTO_MONEY;
    }

    private void checkValidate(final String money) {
        checkValidateInt(money);
        checkDividingMoney(Integer.parseInt(money));
    }

    private void checkValidateInt(final String money) {
        try {
            Integer.parseInt(money);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(ERROR_WRONG_INPUT_MONEY);
        }
    }

    private void checkDividingMoney(final int money) {
        if (!(money >= Money.BASIC_LOTTO_MONEY && money % Money.BASIC_LOTTO_MONEY == 0)) {
            throw new IllegalArgumentException(ERROR_WRONG_DIVIDING_MONEY);
        }
    }

    public int getMoney() {
        return money;
    }
}
