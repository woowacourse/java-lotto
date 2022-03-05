package lotto.domain;

public class Money {

    private static final String ERROR_NOT_DIVIDABLE_FORMAT = "구입 금액은 %d원 단위로 나누어 떨어져야 합니다.";
    private static final String ERROR_NOT_POSITIVE = "구입 금액은 양의 정수 형태로 입력해야 합니다.";
    private static final String ERROR_NOT_ENOUGH = "구입하기에 돈이 충분하지 않습니다.";
    private static final int ZERO = 0;

    private final int inputMoney;

    public Money(int inputMoney, int price) {
        this.inputMoney = inputMoney;

        validateIsNegativeOrZero();
        validateIsDividableBy(price);
    }

    private Money(int change) {
        this.inputMoney = change;

        validateEnoughMoney();
    }

    public Money decrease(int price, int size) {
        int change = inputMoney - price * size;
        return new Money(change);
    }

    public int getMaximumPurchase(int price) {
        return inputMoney / price;
    }

    public double calculateYield(double total) {
        return total / (double) inputMoney;
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

    private void validateEnoughMoney() {
        if (inputMoney < ZERO) {
            throw new RuntimeException(ERROR_NOT_ENOUGH);
        }
    }
}
