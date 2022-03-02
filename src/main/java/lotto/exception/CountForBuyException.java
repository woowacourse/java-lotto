package lotto.exception;

public class CountForBuyException extends IllegalArgumentException {

    public static final String COUNT_FOR_BUY_ONLY_POSITIVE_ERROR_MESSAGE = "구매할 개수는 음수가 될 수 없습니다.";

    public CountForBuyException(String message) {
        super(message);
    }
}
