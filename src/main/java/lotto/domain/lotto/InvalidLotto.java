package lotto.domain.lotto;

public class InvalidLotto extends RuntimeException {
    public InvalidLotto(String msg) {
        super(msg);
    }
}
