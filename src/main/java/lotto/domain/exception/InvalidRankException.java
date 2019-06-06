package lotto.domain.exception;

public class InvalidRankException extends RuntimeException {
    public InvalidRankException(String message) {
        super(message);
    }
}
