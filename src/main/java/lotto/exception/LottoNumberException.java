package lotto.exception;

public class LottoNumberException extends IllegalArgumentException {
    public LottoNumberException(String message) {
        super(message);
    }

    public LottoNumberException() {
        this("로또 숫자는 1~45사이어야 합니다.");
    }
}
