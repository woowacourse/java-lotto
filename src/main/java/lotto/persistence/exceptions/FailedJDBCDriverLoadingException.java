package lotto.persistence.exceptions;

public class FailedJDBCDriverLoadingException extends RuntimeException {
    public FailedJDBCDriverLoadingException(String message) {
        super(message);
    }
}
