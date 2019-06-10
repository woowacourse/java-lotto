package lotto.domain;

public class InvalidRankException extends RuntimeException {
    public InvalidRankException(String message) {
        super(message);
    }
}
