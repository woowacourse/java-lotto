package lotto.exception;

public class InvalidLottoRankException extends RuntimeException{

    private static final String ERROR_MESSAGE = "[ERROR] 올바르지 등수 입니다.";

    public InvalidLottoRankException() {
        super(ERROR_MESSAGE);
    }
}
