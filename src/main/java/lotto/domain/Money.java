package lotto.domain;

public class Money {
    private static final String ERROR_NOT_INTEGER = "[ERROR] 금액은 숫자로 입력해주세요.";
    private static final String ERROR_SHORT_MONEY = "[ERROR] 최소 금액은 1000원입니다.";
    private static final String ERROR_NOT_UNIT = "[ERROR] 금액을 1000원 단위로 입력해주세요";

    private static final int UNIT_AMOUNT = 1000;

    private final int amount;

    public Money(String input) {
        int value = convertToInt(input);
        validateAmount(value);
        this.amount = value;
    }

    private void validateAmount(int value) {
        checkShortMoney(value);
        checkDivideByUnitAmount(value);
    }

    private int convertToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_NOT_INTEGER);
        }
    }

    private void checkShortMoney(int value) {
        if (value < UNIT_AMOUNT) {
            throw new IllegalArgumentException(ERROR_SHORT_MONEY);
        }
    }

    private void checkDivideByUnitAmount(int value) {
        if (value % UNIT_AMOUNT != 0) {
            throw new IllegalArgumentException(ERROR_NOT_UNIT);
        }
    }

    public int getAmount() {
        return amount;
    }
}
