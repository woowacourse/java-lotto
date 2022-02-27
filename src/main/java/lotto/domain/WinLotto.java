package lotto.domain;

public class WinLotto {

    private final Lotto winLotto;
    private final LottoNumber bonusNumber;

    public WinLotto(final Lotto winLotto, final LottoNumber bonusNumber) {
        checkDuplicateBonusNumber(winLotto, bonusNumber);
        this.winLotto = winLotto;
        this.bonusNumber = bonusNumber;
    }

    private void checkDuplicateBonusNumber(final Lotto winLotto, final LottoNumber bonusNumber) {
        if (winLotto.containNumber(bonusNumber)) {
            throw new IllegalArgumentException("[ERROR] 보너스 볼이 당첨 번호와 중복됩니다.");
        }
    }

    public Rank matchResult(final Lotto lotto) {
        final int hitCounts = lotto.calculateMatchCount(winLotto);
        final boolean isBonus = lotto.containNumber(bonusNumber);
        return Rank.calculateCurrentRank(hitCounts, isBonus);
    }
}
