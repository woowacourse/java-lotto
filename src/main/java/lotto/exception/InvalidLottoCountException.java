package lotto.exception;

public class InvalidLottoCountException extends RuntimeException {
    public InvalidLottoCountException() {
        super("로또 번호의 개수는 6개입니다.");
    }
}
