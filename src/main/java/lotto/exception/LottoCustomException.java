package lotto.exception;

public class LottoCustomException extends IllegalArgumentException {
    public static final String ERROR_PREFIX = "[ERROR] ";

    public LottoCustomException(String message) {
        super(ERROR_PREFIX + message);
    }

}
