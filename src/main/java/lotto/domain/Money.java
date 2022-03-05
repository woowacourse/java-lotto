package lotto.domain;

public class Money {

    private static final String ERROR_NOT_DIVIDABLE_FORMAT = "구입 금액은 %d원 단위로 나누어 떨어져야 합니다.";
    private static final String ERROR_NOT_POSITIVE = "구입 금액은 양의 정수 형태로 입력해야 합니다.";
    private static final String ERROR_NEGATIVE = "금액은 음수일 수 없습니다.";
    private static final int ZERO = 0;

    private final int inputMoney;

    public Money(int inputMoney, int price) {
        this.inputMoney = inputMoney;
        validateIsNegativeOrZero();
        validateIsDividableBy(price);
    }

    public Money(int money) {
        this.inputMoney = money;
        validateNegative();
    }

    public Money decrease(int price, int size) {
        int change = inputMoney - price * size;
        return new Money(change);
    }

    public int getMaximumPurchase(int price) {
        return inputMoney / price;
    }

    public double divideBy(int divider) {
        return (double) inputMoney / (double) divider;
    }

    private void validateIsNegativeOrZero() {
        if (inputMoney <= ZERO) {
            throw new RuntimeException(ERROR_NOT_POSITIVE);
        }
    }

    private void validateIsDividableBy(int price) {
        if (inputMoney % price != ZERO) {
            throw new RuntimeException(String.format(ERROR_NOT_DIVIDABLE_FORMAT, price));
        }
    }

    private void validateNegative() {
        if (inputMoney < ZERO) {
            throw new RuntimeException(ERROR_NEGATIVE);
        }
    }
}
