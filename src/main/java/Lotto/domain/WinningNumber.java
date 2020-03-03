package Lotto.domain;

public class WinningNumber {
    private static final String BONUS_CANNOT_BE_DUPLICATE_WITH_WINNING_NUMBER = "보너스넘버는 당첨번호와 중복될 수 없습니다.";

    private Lotto winningLotto;
    private LottoNumber bonusNumber;

    public WinningNumber(Lotto winningLotto, LottoNumber bonusNumber) {
        this.winningLotto = winningLotto;
        if (isBonusNumberDuplicatedWithWinningNumber(winningLotto, bonusNumber)) {
            throw new IllegalArgumentException(BONUS_CANNOT_BE_DUPLICATE_WITH_WINNING_NUMBER);
        }
        this.bonusNumber = bonusNumber;
    }

    private boolean isBonusNumberDuplicatedWithWinningNumber(Lotto winningLotto, LottoNumber bonusNumber) {
        return winningLotto.hasBonusNumber(bonusNumber);
    }

    int countHit(Lotto lotto) {
        return winningLotto.countMatchingAmountWith(lotto);
    }

    boolean hasBonusNumber(Lotto lotto) {
        return lotto.contains(bonusNumber);
    }
}
