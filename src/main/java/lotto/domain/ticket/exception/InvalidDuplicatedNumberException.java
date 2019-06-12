package lotto.domain.ticket.exception;

public class InvalidDuplicatedNumberException extends RuntimeException {
    public InvalidDuplicatedNumberException(String message) {
        super(message);
    }
}
