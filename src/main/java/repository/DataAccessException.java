package repository;

public class DataAccessException extends RuntimeException {
    public DataAccessException(String errorInvalidExecuteUpdateMessage) {
        super(errorInvalidExecuteUpdateMessage);
    }
}
