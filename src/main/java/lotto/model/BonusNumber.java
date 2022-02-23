package lotto.model;

public class BonusNumber {
    public static final String ERROR_OUT_OF_RANGE_BONUS_NUMBER = "보너스 볼 번호가 1~45 범위 내에 해당하지 않습니다.";
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;

    private final Integer number;

    public BonusNumber(Integer number) {
        validateRangeBonusNumber(number);
        this.number = number;
    }

    public Integer getNumber() {
        return number;
    }

    private void validateRangeBonusNumber(Integer bonusNumber) {
        if (bonusNumber < MIN_LOTTO_NUMBER || bonusNumber > MAX_LOTTO_NUMBER) {
            throw new IllegalArgumentException(ERROR_OUT_OF_RANGE_BONUS_NUMBER);
        }
    }
}
