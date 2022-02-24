package lotto.domain;

public class Money {
    private static final String ERROR_NOT_INTEGER = "[ERROR] 금액은 숫자로 입력해주세요.";
    private static final String ERROR_SHORT_MONEY = "[ERROR] 최소 금액은 1000원입니다.";
    private static final String ERROR_NOT_UNIT = "[ERROR] 금액을 1000원 단위로 입력해주세요";

    private static final int UNIT_AMOUNT = 1000;

    private final int amount;

    public Money(String input) {
        isNumberFormat(input);
        int value = Integer.parseInt(input);
        isShortMoney(value);
        isDivideByUnitAmount(value);
        this.amount = value;
    }

    private void isNumberFormat(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_NOT_INTEGER);
        }
    }

    private void isShortMoney(int value) {
        if (value < UNIT_AMOUNT) {
            throw new IllegalArgumentException(ERROR_SHORT_MONEY);
        }
    }

    private void isDivideByUnitAmount(int value) {
        if (value % UNIT_AMOUNT != 0) {
            throw new IllegalArgumentException(ERROR_NOT_UNIT);
        }
    }

    public int getAmount() {
        return amount;
    }
}
