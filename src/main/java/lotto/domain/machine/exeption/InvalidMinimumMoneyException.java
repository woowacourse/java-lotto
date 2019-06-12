package lotto.domain.machine.exeption;

public class InvalidMinimumMoneyException extends RuntimeException {
    public InvalidMinimumMoneyException(String message) {
        super(message);
    }
}
