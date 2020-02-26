package Lotto.domain;

public class WinningNumber {
    private Lotto winningLotto;
    private LottoNumber bonusNumber;

    public WinningNumber(Lotto winningLotto, LottoNumber bonusNumber) {
        this.winningLotto = winningLotto;
        if(bonusNumberDuplicatedWithWinningNumber(winningLotto, bonusNumber)) {
            throw new IllegalArgumentException("보너스넘버는 당첨번호와 중복될 수 없습니다.");
        }
        this.bonusNumber = bonusNumber;
    }

    private boolean bonusNumberDuplicatedWithWinningNumber(Lotto winningLotto, LottoNumber bonusNumber) {
        return winningLotto.hasBonusNumber(bonusNumber);
    }

    public int countHit(Lotto lotto) {
        return winningLotto.countMatchingAmountWith(lotto);
    }

    public boolean checkBonusNumber(Lotto lotto) {
        return lotto.contains(bonusNumber);
    }
}
