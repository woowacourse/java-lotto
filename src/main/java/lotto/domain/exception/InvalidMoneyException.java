package lotto.domain.exception;

public class InvalidMoneyException extends RuntimeException {
    public InvalidMoneyException(String message) {
        super(message);
    }
}
