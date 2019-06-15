package lotto.domain.machine.exeption;

public class InvalidManualNumException extends RuntimeException {
    public InvalidManualNumException(String message) {
        super(message);
    }
}
