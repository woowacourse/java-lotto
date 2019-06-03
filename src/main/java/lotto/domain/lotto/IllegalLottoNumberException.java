package lotto.domain.lotto;

public class IllegalLottoNumberException extends RuntimeException {
    public IllegalLottoNumberException(String message) {
        super(message);
    }
}
