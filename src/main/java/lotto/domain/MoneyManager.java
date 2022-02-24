package lotto.domain;

public class MoneyManager {
    private static final int LOTTO_PRICE = 1000;
    private static final String ERROR_NOT_DIVIDABLE = "구입 금액은 " + LOTTO_PRICE + "원 단위로 나누어 떨어져야 합니다.";
    private static final String ERROR_NOT_POSITIVE = "구입 금액은 양의 정수 형태로 입력해야 합니다.";

    private final int inputMoney;

    public MoneyManager(int inputMoney) {
        this.inputMoney = inputMoney;

        validateIsNegativeOrZero();
        validateIsDividableByLottoPrice();
    }

    private void validateIsDividableByLottoPrice() {
        if (inputMoney % LOTTO_PRICE != 0) {
            throw new RuntimeException(ERROR_NOT_DIVIDABLE);
        }
    }

    private void validateIsNegativeOrZero() {
        if (inputMoney <= 0) {
            throw new RuntimeException(ERROR_NOT_POSITIVE);
        }
    }

    public int getLottoCount() {
        return inputMoney / LOTTO_PRICE;
    }

    public double calculateYield(double total) {
        return Math.round((total / (double) inputMoney) * 100) / 100.0;
    }
}
