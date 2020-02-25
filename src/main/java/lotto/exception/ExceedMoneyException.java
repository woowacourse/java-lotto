package lotto.exception;

public class ExceedMoneyException extends RuntimeException {
    public ExceedMoneyException(String message) {
        super(message);
    }
}
