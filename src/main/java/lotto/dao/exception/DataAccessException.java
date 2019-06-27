package lotto.dao.exception;

public class DataAccessException extends RuntimeException {
    public DataAccessException() {
    }

    public DataAccessException(Throwable cause) {
        super(cause);
    }
}
