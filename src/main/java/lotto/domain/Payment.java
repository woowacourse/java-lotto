package lotto.domain;

public class Payment {
    private static final String NUMBER_MATCHES = "-?[0-9]+";
    private static final String ERROR_ONLY_NUMBER = "숫자를 입력해주세요!";
    private static final String ERROR_ONLY_NATURAL_NUMBER = "자연수를 입력해주세요!";
    private static final int LOTTO_PRICE = 1000;
    private static final String ERROR_LOTTO_PRICE = "1,000원 단위로 입력해주세요!";

    private final int payment;

    public Payment(final String payment) {
        validatePayment(payment);
        this.payment = Integer.parseInt(payment);
    }

    public int getPayment() {
        return payment;
    }

    public int getLottoCount() {
        return this.payment / LOTTO_PRICE;
    }

    private void validatePayment(final String payment) {
        if (isBlank(payment) || !isNumber(payment)) {
            throw new IllegalArgumentException(ERROR_ONLY_NUMBER);
        }
        int number = Integer.parseInt(payment);
        validateNaturalNumber(number);
        validateBuyLotto(number);
    }

    private boolean isBlank(final String text) {
        return text == null || text.isEmpty();
    }

    private boolean isNumber(final String value) {
        return value.matches(NUMBER_MATCHES);
    }

    private void validateNaturalNumber(final int number) {
        if (number <= 0) {
            throw new IllegalArgumentException(ERROR_ONLY_NATURAL_NUMBER);
        }
    }

    private void validateBuyLotto(final int number) {
        if ((number % LOTTO_PRICE) != 0) {
            throw new IllegalArgumentException(ERROR_LOTTO_PRICE);
        }
    }
}
