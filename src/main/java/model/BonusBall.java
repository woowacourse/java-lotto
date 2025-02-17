package model;

import constants.ErrorType;

public class BonusBall {

    private final LottoNumber lottoNumber;

    public BonusBall(final LottoNumber lottoNumber, final WinningNumbers winningNumbers) {
        validateContainsWinningNumbers(lottoNumber, winningNumbers);
        this.lottoNumber = lottoNumber;
    }

    public static BonusBall of(final int number, final WinningNumbers winningNumbers) {
        final LottoNumber lottoNumber = new LottoNumber(number);
        return new BonusBall(lottoNumber, winningNumbers);
    }

    private void validateContainsWinningNumbers(final LottoNumber lottoNumber, final WinningNumbers winningNumbers) {
        if (winningNumbers.containsLottoNumber(lottoNumber)) {
            throw new IllegalArgumentException(ErrorType.BONUS_BALL_IS_DUPLICATION.getMessage());
        }
    }

    public boolean matchBonusNumber(final Lotto lotto) {
        return lotto.isContainsNumber(lottoNumber);
    }
}
