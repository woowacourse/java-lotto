package lotto.exceptions;

public class InvalidLottoNumberException extends RuntimeException {
    public InvalidLottoNumberException() {
        super("잘못된 로또 번호를 입력하셨습니다.");
    }
}
