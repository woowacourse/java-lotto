package lotto.domain.dao;

public class JDBCConnectException extends Throwable {
    public JDBCConnectException(final String message) {
        super(message);
    }
}
