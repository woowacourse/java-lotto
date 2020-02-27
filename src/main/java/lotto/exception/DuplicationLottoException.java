package lotto.exception;

public class DuplicationLottoException extends RuntimeException {
    public DuplicationLottoException() {
        super("로또 번호는 중복될 수 없습니다.");
    }
}
