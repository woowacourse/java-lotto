package lotto.persistence;

public class FailedJDBCDriverLoadingException extends RuntimeException {
    public FailedJDBCDriverLoadingException(String message) {
        super(message);
    }
}
