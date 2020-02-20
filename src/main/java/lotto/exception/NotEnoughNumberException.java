package lotto.exception;

public class NotEnoughNumberException extends IllegalArgumentException {
    public NotEnoughNumberException(String notEnoughNumbers) {
        super(notEnoughNumbers);
    }
}
