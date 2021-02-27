package lotto.exception;

public class InvalidLottoNumberException extends IllegalArgumentException {

    public InvalidLottoNumberException() {
        super("[ERROR] 로또 번호는 1부터 45사이의 값이어야 합니다.");
    }
}
