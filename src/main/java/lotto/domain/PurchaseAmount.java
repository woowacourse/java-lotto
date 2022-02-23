package lotto.domain;

public class PurchaseAmount {
    private static final String NUMBER_MATCHES = "^[0-9]*";
    private static final int LOTTO_PRICE  = 1000;
    private static final String ERROR_PREFIX = "[ERROR] ";
    private static final String ERROR_ONLY_NUMBER = "숫자를 입력해주세요!";
    private static final String ERROR_ONLY_NATURAL_NUMBER = "자연수를 입력해주세요!";
    private static final String ERROR_LOTTO_PRICE = "1,000원 단위로 입력해주세요!";

    private final int purchaseAmount;

    public PurchaseAmount(final String purchaseAmount) {
        checkValidValue(purchaseAmount);
        this.purchaseAmount = Integer.parseInt(purchaseAmount);
    }

    private void checkValidValue(final String value) {
        if (isBlank(value) || isNumber(value)) {
            throw new IllegalArgumentException(ERROR_PREFIX + ERROR_ONLY_NUMBER);
        }
        int number = Integer.parseInt(value);
        checkNaturalNumber(number);
        checkDivideLottoPrice(number);
    }

    private boolean isBlank(final String value) {
        return value == null || value.isEmpty();
    }

    private boolean isNumber(final String value) {
        return value.matches(NUMBER_MATCHES);
    }

    private void checkNaturalNumber(final int value) {
        if (value <= 0) {
            throw new IllegalArgumentException(ERROR_PREFIX + ERROR_ONLY_NATURAL_NUMBER);
        }
    }

    private void checkDivideLottoPrice(final int value) {
        if ((value % LOTTO_PRICE) != 0) {
            throw new IllegalArgumentException(ERROR_PREFIX + ERROR_LOTTO_PRICE);
        }
    }
}
