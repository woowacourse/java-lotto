package model;

public class BonusNumber {

    private static final int START_NUMBER_OF_LOTTO_RANGE = 1;
    private static final int END_NUMBER_OF_LOTTO_RANGE = 45;
    private final int bonusNumber;

    public BonusNumber(final int bonusNumber) {
        validateNumberRange(bonusNumber);
        this.bonusNumber = bonusNumber;
    }

    public int getBonusNumber() {
        return bonusNumber;
    }

    private void validateNumberRange(int bonusNumber) {
        if (bonusNumber < START_NUMBER_OF_LOTTO_RANGE || bonusNumber > END_NUMBER_OF_LOTTO_RANGE) {
            throw new IllegalArgumentException("숫자는 1~45 사이여야 합니다.");
        }
    }

    public boolean isBonusMatch(Lotto lotto) {
        return lotto.contains(bonusNumber);
    }

}
