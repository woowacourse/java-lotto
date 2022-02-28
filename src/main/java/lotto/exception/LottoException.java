package lotto.exception;

public class LottoException extends IllegalArgumentException {

    private static final String ERROR_MESSAGE_PREFIX = "[ERROR] ";

    public LottoException(LottoExceptionStatus lottoExceptionStatus) {
        super(ERROR_MESSAGE_PREFIX + lottoExceptionStatus.getMessage());
    }

}
