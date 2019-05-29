package lotto.domain.exceptions;

public class ManualCountBoundException extends IllegalArgumentException {
    public ManualCountBoundException() {
        super();
    }

    public ManualCountBoundException(String message) {
        super(message);
    }

    public ManualCountBoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ManualCountBoundException(Throwable cause) {
        super(cause);
    }
}
