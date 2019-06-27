package lotto.domain.dao;

public class DataAccessException extends RuntimeException {
    public DataAccessException(final Throwable cause) {
        super(cause);
    }
}
