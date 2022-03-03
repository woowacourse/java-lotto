package lotto.domain;

public class Money {
    public static final int UNIT_AMOUNT = 1000;

    private static final String ERROR_SHORT_MONEY = "[ERROR] 최소 금액은 1000원입니다.";
    private static final String ERROR_NOT_UNIT = "[ERROR] 금액을 1000원 단위로 입력해주세요";

    private final int amount;

    public Money(int value) {
        validateAmount(value);
        this.amount = value;
    }

    private void validateAmount(int value) {
        checkShortMoney(value);
        checkDivideByUnitAmount(value);
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
