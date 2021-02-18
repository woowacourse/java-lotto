package lotto.exception;

public class InvalidLottoHitCountException extends RuntimeException{

    private static final String ERROR_MESSAGE = "[ERROR] 올바르지 않은 히트 카운트입니다.";

    public InvalidLottoHitCountException() {
        super(ERROR_MESSAGE);
    }
}
