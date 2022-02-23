package lotto.exception;

public class WinningNumbersException extends IllegalArgumentException {

    public static final String WINNING_NUMBERS_SIZE_ERROR_MESSAGE = "당첨 번호는 6개여야 합니다.";
    public static final String WINNING_NUMBERS_DUPLICATION_ERROR_MESSAGE = "당첨 번호는 중복될 수 없습니다.";

    public WinningNumbersException(String message) {
        super(message);
    }
}
