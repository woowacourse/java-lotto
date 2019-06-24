package lotto.persistence.exceptions;

public class FailedDBConnectionException extends RuntimeException {
    public FailedDBConnectionException(String message) {
        super(message);
    }
}
