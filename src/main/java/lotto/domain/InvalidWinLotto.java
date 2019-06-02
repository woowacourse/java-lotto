package lotto.domain;

public class InvalidWinLotto extends RuntimeException {
    public InvalidWinLotto(String message) {
        super(message);
    }
}
