package lotto.exception;

public class DuplicateLottoNumberException extends RuntimeException {

    private static final String ERROR_MESSAGE = "[ERROR] 중복된 로또 번호입니다.";

    public DuplicateLottoNumberException() {
        super(ERROR_MESSAGE);
    }
}
