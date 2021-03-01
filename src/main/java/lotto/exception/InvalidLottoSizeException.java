package lotto.exception;

public class InvalidLottoSizeException extends RuntimeException {

    private static final String ERROR_MESSAGE = "[ERROR] 올바르지 않은 로또 번호 개수입니다.";
    
    public InvalidLottoSizeException() {
        super(ERROR_MESSAGE);
    }
}
