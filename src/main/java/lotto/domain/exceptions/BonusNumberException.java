package lotto.domain.exceptions;

public class BonusNumberException extends IllegalArgumentException {
    public BonusNumberException() {
        super();
    }

    public BonusNumberException(String message) {
        super(message);
    }

    public BonusNumberException(String message, Throwable cause) {
        super(message, cause);
    }

    public BonusNumberException(Throwable cause) {
        super(cause);
    }
}
