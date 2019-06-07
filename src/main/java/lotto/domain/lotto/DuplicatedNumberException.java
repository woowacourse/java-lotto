package lotto.domain.lotto;

public class DuplicatedNumberException extends RuntimeException {
    public DuplicatedNumberException() {
    }

    public DuplicatedNumberException(String message) {
        super(message);
    }
}
