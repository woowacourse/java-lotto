package lotto.domain;

public class MoneyManager {

    private static final int LOTTO_PRICE = 1000;
    private static final int DIVIDABLE = 0;
    private static final String ERROR_NOT_DIVIDABLE = "구입 금액은 " + LOTTO_PRICE + "원 단위로 나누어 떨어져야 합니다.";
    private static final String ERROR_NOT_POSITIVE = "구입 금액은 양의 정수 형태로 입력해야 합니다.";
    public static final String ERROR_LESS_THAN_INPUT_MONEY = "구입 금액이 부족합니다.";

    private int inputMoney;

    public MoneyManager(int inputMoney) throws RuntimeException {
        validateIsNegativeOrZero(inputMoney);
        validateIsDividableByLottoPrice(inputMoney);

        this.inputMoney = inputMoney;
    }

    public void decreaseMoney(int manualCount) throws RuntimeException {
        checkManualCountWithInputMoney(manualCount);
        inputMoney -= manualCount * LOTTO_PRICE;
    }

    private void checkManualCountWithInputMoney(int manualCount) {
        if (inputMoney - manualCount * LOTTO_PRICE < 0) {
            throw new RuntimeException(ERROR_LESS_THAN_INPUT_MONEY);
        }
    }

    private void validateIsNegativeOrZero(int inputMoney) {
        if (inputMoney <= 0) {
            throw new RuntimeException(ERROR_NOT_POSITIVE);
        }
    }

    private void validateIsDividableByLottoPrice(int inputMoney) {
        if (inputMoney % LOTTO_PRICE != DIVIDABLE) {
            throw new RuntimeException(ERROR_NOT_DIVIDABLE);
        }
    }

    public int getPossibleLottoCount() {
        return inputMoney / LOTTO_PRICE;
    }

    public double calculateYield(double total) {
        return total / (double) inputMoney;
    }
}
