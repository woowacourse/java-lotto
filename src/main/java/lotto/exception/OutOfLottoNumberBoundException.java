package lotto.exception;

public class OutOfLottoNumberBoundException extends RuntimeException {
    public OutOfLottoNumberBoundException() {
        super("로또 번호 범위를 벗어났습니다.");
    }
}
