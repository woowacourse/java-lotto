package model;

import exception.BonusExceptionType;

public class Bonus {

    private final int number;

    public static Bonus of(final int number, final Lotto lotto) {
        return new Bonus(number, lotto);
    }

    public Bonus(final int number, final Lotto lotto) {
        validateRange(number);
        validateDuplicateWithLotto(number, lotto);
        this.number = number;
    }

    private void validateRange(final int number) {
        if (LottoConstant.MIN_NUMBER > number || number > LottoConstant.MAX_NUMBER) {
            throw new IllegalArgumentException(
                    BonusExceptionType.INVALID_BONUS_RANGE.getMessage(LottoConstant.MIN_NUMBER,
                            LottoConstant.MAX_NUMBER));
        }
    }

    private void validateDuplicateWithLotto(final int number, final Lotto lotto) {
        if (lotto.getNumbers().contains(number)) {
            throw new IllegalArgumentException(BonusExceptionType.BONUS_DUPLICATE.getMessage());
        }
    }

    public int getNumber() {
        return number;
    }
}
