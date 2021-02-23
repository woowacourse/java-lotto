package lotto.exception;

public class InvalidSelfLottoCountException extends RuntimeException {

    private static final String ERROR_MESSAGE = "[ERROR] 잘못된 수동 구매 개수입니다.";

    public InvalidSelfLottoCountException() {
        super(ERROR_MESSAGE);
    }
}