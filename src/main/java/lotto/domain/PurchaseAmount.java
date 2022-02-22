package lotto.domain;

public class PurchaseAmount {
    private static final String NUMBER_MATCHES = "^[0-9]*";
    private static final int LOTTO_PRICE  = 1000;
    private static final String ERROR_PREFIX = "[ERROR] ";
    private static final String ERROR_ONLY_NUMBER = "숫자를 입력해주세요!";
    private static final String ERROR_ONLY_NATURAL_NUMBER = "자연수를 입력해주세요!";
    private static final String ERROR_LOTTO_PRICE = "1,000원 단위로 입력해주세요!";

    private final int purchaseAmount;

    public PurchaseAmount(String purchaseAmount) {
        checkValidValue(purchaseAmount);
        this.purchaseAmount = Integer.parseInt(purchaseAmount);
    }

    private void checkValidValue(String value) {
        if (isBlank(value) || isNumber(value)) {
            throw new IllegalArgumentException(ERROR_PREFIX + ERROR_ONLY_NUMBER);
        }
        if (isNegative(value)) {
            throw new IllegalArgumentException(ERROR_PREFIX + ERROR_ONLY_NATURAL_NUMBER);
        }
        if (isDivideLottoPrice(value)) {
            throw new IllegalArgumentException(ERROR_PREFIX + ERROR_LOTTO_PRICE);
        }
    }

    private boolean isBlank(String value) {
        return value == null || value.isEmpty();
    }

    private boolean isNumber(String value) {
        return value.matches(NUMBER_MATCHES);
    }

    private boolean isNegative(String value) {
        return Integer.parseInt(value) < 0;
    }

    private boolean isDivideLottoPrice(String value) {
        return (Integer.parseInt(value) % LOTTO_PRICE) == 0;
    }
}
