package domain;

public class WinningLotto {

    private final Lotto winningLotto;
    private final LottoNumber bonusNumber;

    public WinningLotto(Lotto winningLotto, LottoNumber bonusNumber) {
        validateDuplicate(winningLotto, bonusNumber);
        this.winningLotto = winningLotto;
        this.bonusNumber = bonusNumber;
    }

    private void validateDuplicate(Lotto winningLotto, LottoNumber bonusNumber) {
        if (winningLotto.containsNumber(bonusNumber)) {
            throw new IllegalArgumentException("보너스 볼은 당첨번호와 중복될 수 없습니다.");
        }
    }

    public LottoReward match(Lotto lotto) {
        int matchCount = calculateMatchCount(lotto);
        boolean hasBonus = lotto.containsNumber(bonusNumber);

        return LottoReward.find(matchCount, hasBonus);
    }

    private int calculateMatchCount(Lotto otherLotto) {
        return winningLotto.calculateSameNumber(otherLotto);
    }
}
