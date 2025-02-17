package model;

public class WinningLotto {

    private final WinningNumbers winningNumbers;
    private final BonusBall bonusBall;

    public WinningLotto(final WinningNumbers winningNumbers, final BonusBall bonusBall) {
        this.winningNumbers = winningNumbers;
        this.bonusBall = bonusBall;
    }

    public boolean containsLottoNumber(final LottoNumber lottoNumber) {
        return winningNumbers.containsLottoNumber(lottoNumber);
    }

    public boolean matchBonusNumber(final Lotto lotto) {
        return bonusBall.matchBonusNumber(lotto);
    }


}
