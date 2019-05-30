package lotto.domain.domainexception;

public class InvalidCustomCountException extends RuntimeException {
    public InvalidCustomCountException(String message) {
        super(message);
    }
}
