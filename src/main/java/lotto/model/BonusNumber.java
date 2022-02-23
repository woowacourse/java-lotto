package lotto.model;

public class BonusNumber {
    public static final String ERROR_OUT_OF_RANGE_BONUS_NUMBER = "보너스 볼 번호가 1~45 범위 내에 해당하지 않습니다.";

    private final Integer number;

    public BonusNumber(Integer number) {
        validateRangeBonusNumber(number);
        this.number = number;
    }

    public Integer getNumber() {
        return number;
    }

    private void validateRangeBonusNumber(Integer bonusNumber) {
        if (bonusNumber < 1 || bonusNumber > 45) {
            throw new IllegalArgumentException(ERROR_OUT_OF_RANGE_BONUS_NUMBER);
        }
    }
}
