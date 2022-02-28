package lotto.domain;

public class Money {

    private static final int LOTTO_PRICE = 1000;
    private static final String ERROR_NOT_DIVIDABLE = "구입 금액은 " + LOTTO_PRICE + "원 단위로 나누어 떨어져야 합니다.";
    private static final String ERROR_NOT_POSITIVE = "구입 금액은 양의 정수 형태로 입력해야 합니다.";
    private static final int DIVIDABLE = 0;
    public static final int NONE_MONEY = 0;

    private final int inputMoney;

    public Money(int inputMoney) {
        this.inputMoney = inputMoney;

        validateIsNegativeOrZero();
        validateIsDividableByLottoPrice();
    }

    private void validateIsDividableByLottoPrice() {
        if (inputMoney % LOTTO_PRICE != DIVIDABLE) {
            throw new RuntimeException(ERROR_NOT_DIVIDABLE);
        }
    }

    private void validateIsNegativeOrZero() {
        if (inputMoney <= NONE_MONEY) {
            throw new RuntimeException(ERROR_NOT_POSITIVE);
        }
    }

    public int getLottoCount() {
        return inputMoney / LOTTO_PRICE;
    }

    public double calculateYield(double total) {
        return total / (double) inputMoney;
    }
}
