package lotto.exception;

public class MoneyException extends RuntimeException {
    public static final String ERROR_MESSAGE = "구입금액은 1000원 이상이여야 합니다.";

    public MoneyException() {
        super(ERROR_MESSAGE);
    }
}