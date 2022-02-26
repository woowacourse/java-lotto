package lotto.model;

public class BonusNumber extends LottoNumber {

    private static final String BONUS_NUMBER_RANGE_ERROR_MESSAGE = "[ERROR] 유효한 보너스 숫자가 아닙니다.";

    public BonusNumber(int bonusNumber) {
        super(bonusNumber);
    }

    @Override
    protected void validateRange(int number) {
        if (number < 1 || number > 45) {
            throw new RuntimeException(BONUS_NUMBER_RANGE_ERROR_MESSAGE);
        }
    }
}
