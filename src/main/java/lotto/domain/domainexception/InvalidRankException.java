package lotto.domain.domainexception;

public class InvalidRankException extends RuntimeException {
    public InvalidRankException(String message) {
        super(message);
    }
}
