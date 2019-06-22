package lotto.domain.exceptions;

public class InvalidRankException extends RuntimeException {
    public InvalidRankException(String message) {
        super(message);
    }
}
