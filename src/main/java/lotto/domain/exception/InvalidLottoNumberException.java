package lotto.domain.exception;

public class InvalidLottoNumberException extends RuntimeException {
    public InvalidLottoNumberException() {
        super("로또 번호는 1이상, 45이하여야 합니다.");
    }
}
