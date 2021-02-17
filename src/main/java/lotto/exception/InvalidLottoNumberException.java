package lotto.exception;

public class InvalidLottoNumberException extends RuntimeException {

    private static final String ERROR_MESSAGE = "[ERROR] 올바르지 않은 로또 번호입니다.";

    public InvalidLottoNumberException() {
        super(ERROR_MESSAGE);
    }
}
