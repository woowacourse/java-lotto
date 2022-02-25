package lotto.domain;

import java.util.Objects;

public class WinLotto {

    private final Lotto winLotto;
    private final LottoNumber bonusNumber;

    public WinLotto(final Lotto winLotto, final LottoNumber bonusNumber) {
        this.winLotto = Objects.requireNonNull(winLotto, "[ERROR] 우승 번호는 null이 들어올 수 없습니다.");
        this.bonusNumber = Objects.requireNonNull(bonusNumber, "[ERROR] 보너스 번호는 null이 들어올 수 없습니다.");
        checkDuplicateBonusNumber(this.winLotto, this.bonusNumber);
    }

    private void checkDuplicateBonusNumber(final Lotto winLotto, final LottoNumber bonusNumber) {
        if (winLotto.containNumber(bonusNumber)) {
            throw new IllegalArgumentException("[ERROR] 보너스 볼이 당첨 번호와 중복됩니다.");
        }
    }

    public Rank matchResult(final Lotto lotto) {
        final int hitCounts = lotto.matchingCounts(winLotto);
        final boolean isBonus = lotto.containNumber(bonusNumber);
        return Rank.calculateCurrentRank(hitCounts, isBonus);
    }
}
