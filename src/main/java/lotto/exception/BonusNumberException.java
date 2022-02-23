package lotto.exception;

public class BonusNumberException extends IllegalArgumentException {

    public static final String BONUS_NUMBER_DUPLICATION_ERROR_MESSAGE = "보너스 번호는 당첨 번호와 중복될 수 없습니다.";

    public BonusNumberException(String message) {
        super(message);
    }
}
