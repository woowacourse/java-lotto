package lotto.domain.exception;

public class RankNotExistException extends RuntimeException {
    public RankNotExistException(String message) {
        super(message);
    }
}
