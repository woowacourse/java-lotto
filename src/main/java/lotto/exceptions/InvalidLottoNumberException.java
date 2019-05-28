package lotto.exceptions;

public class InvalidLottoNumberException extends RuntimeException {
    public InvalidLottoNumberException() {
    }

    public InvalidLottoNumberException(String message) {
        super(message);
    }

    public InvalidLottoNumberException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidLottoNumberException(Throwable cause) {
        super(cause);
    }

    public InvalidLottoNumberException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
