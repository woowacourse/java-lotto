package lotto.domain.exceptions;

public class LottoNumberException extends RuntimeException {
    public LottoNumberException() {
    }

    public LottoNumberException(String message) {
        super(message);
    }

    public LottoNumberException(String message, Throwable cause) {
        super(message, cause);
    }

    public LottoNumberException(Throwable cause) {
        super(cause);
    }

    public LottoNumberException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
