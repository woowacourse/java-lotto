package lotto.exception;

public class InvalidLottoNumberCountException extends RuntimeException {

    private static final String ERROR_MESSAGE = "[ERROR] 로또 번호는 6개의 수를 입력해야 합니다.";

    public InvalidLottoNumberCountException() {
        super(ERROR_MESSAGE);
    }
}
