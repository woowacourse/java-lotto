package lotto.persistence;

public class FailedDBConnectionException extends RuntimeException {
    public FailedDBConnectionException(String message) {
        super(message);
    }
}
